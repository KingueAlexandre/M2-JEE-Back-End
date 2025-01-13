package fr.uge.jee.springmvc.pokematch;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Pokemon(
    @JsonProperty("name")
    String name,
    @JsonProperty("url")
    String url){


    @Override
    public String toString() {
        return "Pokemon{name=" + name + ", url='" + url + "}";
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
