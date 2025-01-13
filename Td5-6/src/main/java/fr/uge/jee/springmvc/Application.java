package fr.uge.jee.springmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("https://localhost:8080/pokemons");
    }


    @Bean
    WebClient getWebClient(WebClient.Builder defaultBuilder) {
        return defaultBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)).build()).build();
    }
//    @Bean
//    WebClient getWebClient(WebClient.Builder defaultBuilder) {
//        return defaultBuilder.exchangeStrategies(ExchangeStrategies.builder()
//                .codecs(configurer -> configurer
//                        .defaultCodecs()
//                        .maxInMemorySize(16 * 1024 * 1024)).build())
//                .baseUrl("https://pokeapi.co/api/v2/") // Base URL pour l'API Pokémon
//                .build();
//    }
//    @Bean
//    public CommandLineRunner printAllBeans(ApplicationContext applicationContext) {
//        return args -> {
////            WebClient webClient = WebClient.create();
////            Student student = webClient.get()
////                    .uri("http://localhost:8080/students/1")
////                    .retrieve()
////                    .bodyToMono(Student.class)
////                    .block();
////            System.out.println(student);
//        };
//    }
}

