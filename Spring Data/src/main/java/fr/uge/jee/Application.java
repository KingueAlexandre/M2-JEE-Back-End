package fr.uge.jee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner cmd(PokemonService pokemonService){
        return args -> {
            var threads = new ArrayList<Thread>();
            for (int i = 0; i < 100; i++) {
                var thread=new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        var name = ""+ ThreadLocalRandom.current().nextInt(100);
                        pokemonService.insertOrIncrementPokemon("balbuzar"+name);
                    }
                });
                threads.add(thread);
                thread.start();
            }
            for(var thread : threads){
                thread.join();
            }
            System.out.println(pokemonService.totalCountVote());
        };
    }
}