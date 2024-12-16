package fr.uge.jee.springmvc.pokematch;

import java.util.List;

public class PokeList {
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
