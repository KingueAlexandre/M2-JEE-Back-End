package fr.uge.jee.springmvc.pokematch;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class PokemonServices {
    @Value("${pokematch.pokemonServices.nbTopFetish}") private int nbTopFetish;
    private final WebClient webClient;
    private final List<Pokemon> results;
    private final Map<Pokemon,Integer> mapFetish = new HashMap<>();
    private final Map<Pokemon,Sprites> mapSprite = new HashMap<>();
    private final Map<Sprites,byte[]> mapCache = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    PokemonServices(WebClient webClient) {
        this.webClient = webClient;
        this.results = fetchPokemonsGraphQL();
//        this.results = fetchPokemons();
    }


    private record PokemonResponse(List<Pokemon> results)  {
    }
//    public record PokemonGraphQLResponse(@JsonProperty("data") Data data){
//        public List<Pokemon> pokemons() {
//            return data.pokemon_v2_pokemon.stream()
//                    .map(elem -> new Pokemon(elem.name,"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+elem.pokemon_v2_pokemonsprites().id+".png"))
//                    .collect(Collectors.toList());
//        }
//        public record Data (@JsonProperty("pokemon_v2_pokemon")  List<PokemonData> pokemon_v2_pokemon){
//        }
//
//        public record PokemonData (@JsonProperty("name") String name, @JsonProperty("pokemon_v2_pokemonsprites") Sprites pokemon_v2_pokemonsprites){
//        }
//
//        public record Sprites (@JsonProperty("id") int id){
//        }
//    }

    private record GraphQLPokemonResponse(Data data) {
        record Data(List<PokemonEntry> pokemon_v2_pokemon) {}
        record PokemonEntry(String name,String url, int id) {
        }
        /**
         * Get data of data and put it in a list of pokemon
         * @return
         */
        public List<Pokemon> pokemons() {
            return data.pokemon_v2_pokemon().stream()
                    .map(e-> new Pokemon(e.name, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+e.id()+".png"))
                    .toList();
        }
    }


    private List<Pokemon> fetchPokemons() {
        var response = webClient.get()
                .uri("https://pokeapi.co/api/v2/pokemon?limit=40")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block();
        if(response != null) {
            for (var pokemon : response.results) {
                if (pokemon.url() != null) {
                    var spritePokemon = Objects.requireNonNull(webClient.get()
                                    .uri(pokemon.url())
                                    .retrieve()
                                    .bodyToMono(PokemonPage.class)
                                    .block())
                            .sprites();
                    lock.lock();
                    try {
                        mapSprite.put(pokemon, spritePokemon);
                    } finally {
                        lock.unlock();
                    }
                }
            }
            return response.results();
        }
        return null;
    }

    private List<Pokemon> fetchPokemonsGraphQL() {
//        String query = """
//            {
//                pokemon_v2_pokemon(limit:40){
//                    name
//                    pokemon_v2_pokemonsprites {
//                        id
//                    }
//                }
//            }""";
//        var responseBlock = webClient.post()
//                .uri("https://beta.pokeapi.co/graphql/v1beta")
//                .header("Content-Type", "application/json")
//
//                .bodyValue(Map.of("query", query))
//                .retrieve()
//                .bodyToMono(PokemonGraphQLResponse.class)
//                .block();
        String query = """
        {
          pokemon_v2_pokemon(limit: 40) {
            name
            id
          }
        }
        """;
        var responseBlock = webClient.post()
                .uri("https://beta.pokeapi.co/graphql/v1beta")
                .header("Content-Type", "application/json")
                .bodyValue(Map.of("query", query))
                .retrieve()
                .bodyToMono(GraphQLPokemonResponse.class)
                .block();
        var response = Objects.requireNonNull(responseBlock).pokemons();
        System.out.println(response);
        if (response == null) {
            System.out.println("Aucune réponse reçue");
            return Collections.emptyList(); // Retourner une liste vide si aucune réponse
        }
        for (var pokemon : response) {
            if (pokemon.url() != null) {
                var spritePokemon = new Sprites(pokemon.url());
                lock.lock();
                try {
                    mapSprite.put(pokemon, spritePokemon);
                } finally {
                    lock.unlock();
                }
            }        }
        return response;
    }

    private void getSpritreFromURL(Pokemon pokemon) {
        if (pokemon.url() != null) {
            var spritePokemon = Objects.requireNonNull(webClient.get()
                            .uri(pokemon.url())
                            .retrieve()
                            .bodyToMono(PokemonPage.class)
                            .block())
                    .sprites();
            lock.lock();
            try {
                mapSprite.put(pokemon, spritePokemon);
            } finally {
                lock.unlock();
            }
        }
    }

    public Pokemon closestPokemon(User user) {
        lock.lock();
        try {
            var poke = results.stream().min(Comparator.comparingInt(e -> Math.abs(e.hashCode() - user.hashCode()))).orElse(null);
            addFetish(poke);
            return poke;
        }finally {
            lock.unlock();
        }
    }

    private void addFetish(Pokemon pokemon) {
        lock.lock();
        try {
            if (mapFetish.containsKey(pokemon)) {
                mapFetish.computeIfPresent(pokemon, (k, v) -> v + 1);
            } else {
                mapFetish.computeIfAbsent(pokemon, k -> 1);
            }
        }finally {
            lock.unlock();
        }
    }

    public List<Pokemon> getTopFetish(){
        lock.lock();
        try {
            return mapFetish.keySet().stream().sorted(Comparator.comparingInt(mapFetish::get).reversed()).limit(nbTopFetish).toList();
        }finally {
            lock.unlock();
        }
    }

    public Sprites getSpritePokemon(Pokemon pokemon) {
        lock.lock();
        try {
            return mapSprite.get(pokemon);
        }finally {
            lock.unlock();
        }
    }

    public String getImageDL(Pokemon pokemon){
        lock.lock();
        try{
            var sprites = getSpritePokemon(pokemon);
            if(mapCache.containsKey(sprites)){
                return Base64.getEncoder().encodeToString(mapCache.get(sprites));
            }
            var image = downloadbyteImage(sprites);
            mapCache.put(sprites,image);
            return Base64.getEncoder().encodeToString(image);
        } finally {
            lock.unlock();
        }
    }

    private byte[] downloadbyteImage(Sprites sprites){
        Mono<byte[]> imageBytesMono = webClient.get()
                .uri(sprites.front_default())
                .retrieve()
                .bodyToMono(byte[].class);
        return imageBytesMono.block();
    }

}
