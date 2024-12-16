package fr.uge.jee.springmvc.pokematch;

import fr.uge.jee.springmvc.reststudents.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(fr.uge.jee.springmvc.reststudents.Application.class, args);

    }

    @Bean
    WebClient getWebClient(WebClient.Builder defaultBuilder) {
        return defaultBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)).build())
                .baseUrl("https://pokeapi.co/api/v2/") // Base URL pour l'API Pokémon
                .build();
    }
    @Bean
    public CommandLineRunner printAllBeans(ApplicationContext applicationContext) {
        return args -> {
//            WebClient webClient = WebClient.create();
//            Student student = webClient.get()
//                    .uri("http://localhost:8080/students/1")
//                    .retrieve()
//                    .bodyToMono(Student.class)
//                    .block();
//            System.out.println(student);
        };
    }
}

