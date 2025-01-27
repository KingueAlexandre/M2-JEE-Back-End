package fr.uge.jee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon,Long>
{
    Optional<Pokemon> findByName(String name);
    Set<Pokemon> findAllByNameOrderByScoreAsc(String name);
    void update(Pokemon pokemon);
}
