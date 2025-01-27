package fr.uge.jee;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    @PersistenceUnit
    private final EntityManagerFactory emf;

    @PersistenceContext
    private final EntityManager em;


    public PokemonService(PokemonRepository pokemonRepository,
                          EntityManagerFactory emf,
                          EntityManager em){
        this.pokemonRepository = pokemonRepository;
        this.emf = emf;
        this.em = em;
    }

    public void insertOrIncrementPokemon(String name) {
        var pokemonOptional = pokemonRepository.findByName(name);
        if(pokemonOptional.isPresent()){
            var pokemon = pokemonOptional.get();
            pokemon.setScore(pokemon.getScore()+1);
            pokemonRepository.update(pokemon);
        }else {

        }

    }
    public long totalCountVote(String name) {
        var pokemonOptional = pokemonRepository.findAllByNameOrderByScoreAsc(name);

    }
}
