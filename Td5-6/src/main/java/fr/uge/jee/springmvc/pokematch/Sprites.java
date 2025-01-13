package fr.uge.jee.springmvc.pokematch;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Sprites (
    @JsonProperty("front_default")
    String front_default){
    }
