package fr.uge.jee.springmvc.pokematch;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.util.Map;
import java.util.Objects;

@Controller
public class PokemonRestController {

    private final Map<Long, Pokemon> pokemons = Map.of();
    private final PokemonServices pokemonServices;

    @Autowired
    public PokemonRestController(PokemonServices pokemonServices) {
        this.pokemonServices = Objects.requireNonNull(pokemonServices);
    }


    @GetMapping("/pokemons")
    public String showForm(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        return "pokemon-form";
    }

    @PostMapping("/pokemons")
    public String processForm(@ModelAttribute @Valid User user,
                              BindingResult result,
                              Model model) throws IOException, InterruptedException {
        if (result.hasErrors()) {
            return "pokemon-form";
        }
        var pokemon = pokemonServices.closestPokemon(user);
        model.addAttribute("pokemon", pokemon);
        var lst = pokemonServices.getTopFetish();
        model.addAttribute("topFetish", lst);

        var sprite = pokemonServices.getSpritePokemon(pokemon);
        model.addAttribute("pokemonSprite", sprite);

        var spritesDL = pokemonServices.getImageDL(pokemon);
        System.out.println("IMAGE FROM CACHE : " + spritesDL);
        model.addAttribute("pokemonSpriteCached", spritesDL);


        return "pokemon-form";
    }

}
