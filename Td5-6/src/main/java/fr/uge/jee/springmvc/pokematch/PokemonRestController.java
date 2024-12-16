package fr.uge.jee.springmvc.pokematch;

import fr.uge.jee.springmvc.reststudents.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
public class PokemonRestController {

    private final Map<Long, Pokemon> pokemons = Map.of();

    @Autowired
    private WebClient webClient;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("pokemon-form");
        return "pokemonForm";
    }


    @GetMapping("/pokemons/{id}")
    public Pokemon getPokemon(@PathVariable("id") long id) {
        var pokemon = pokemons.get(id);
        if (pokemon==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No student with id ("+id+")");
        } else {
            return pokemon;
        }
    }

    @GetMapping("/pokemons")
    public String givePokemon(Model model) {
        model.addAttribute("name", "Arnaud");
        System.out.println("PokemonRestController: GetMapping givePokemon");
        return "pokemon-form";
    }

    Mono<Student> pokemon(int id){
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon/"+id)
                .retrieve()
                .bodyToMono(Student.class);
    }

    private List<Pokemon> getFirst40Pokemons() {
        // Appel API pour récupérer les 40 premiers pokémons
        return webClient.get()
                .uri("/pokemon?limit=40")
                .retrieve()
                .bodyToMono(PokeList.class)
                .map(response -> response.getResults())
                .block();
    }

    private Pokemon findClosestPokemon(List<Pokemon> pokemons, int targetHashcode) {
        return pokemons.stream()
                .min((p1, p2) -> Integer.compare(
                        Math.abs(p1.name().hashCode() - targetHashcode),
                        Math.abs(p2.name().hashCode() - targetHashcode)))
                .orElseThrow(() -> new RuntimeException("Aucun Pokémon trouvé"));
    }

    @PostMapping("/pokemons")
    public String processForm(@ModelAttribute("rectangle-model") Pokemon pokemon,
                              BindingResult result,
                              Model model){
        return "hello";
    }


}
