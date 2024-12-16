package fr.uge.jee.springmvc.pokematch;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Pokemon(
        @JsonIgnore
        String name,
        @JsonProperty("url")
        String url
){

    @Override
    public String toString() {
        return "Student{name=" + name + ", url='" + url + "}";
    }


}
