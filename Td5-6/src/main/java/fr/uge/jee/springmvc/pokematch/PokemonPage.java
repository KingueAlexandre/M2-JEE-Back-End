package fr.uge.jee.springmvc.pokematch;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokemonPage(
        @JsonProperty("sprites")
        Sprites sprites
) {
}
