package fr.uge.jee.springmvc.pokematch;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Service
public class PokemonServices {
    private final WebClient webClient;
    private final List<Pokemon> results;
    PokemonServices(WebClient webClient) {
        this.webClient = webClient;
        this.results = fetchPokemons();
    }


    private record PokemonResponse(List<Pokemon> results)  {
    }

    private List<Pokemon> fetchPokemons() {
        var response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon?limit=151")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block();
        return response.results();
    }


    public Pokemon closestPokemon(User user) {
//        var poke = results.get(1);
        var poke = results.stream().min(Comparator.comparingInt(e -> Math.abs(e.hashCode() - user.hashCode()))).orElse(null);
        results.stream().sorted(Comparator.comparingInt(Pokemon::hashCode)).forEach(e -> System.out.println(e.name() + " :" +e.hashCode()));
        System.out.println(user.hashCode());
        if (poke != null) {
            System.out.println(poke);
        }
        return poke;
    }
}
