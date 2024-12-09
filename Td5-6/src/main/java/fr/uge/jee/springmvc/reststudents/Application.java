package fr.uge.jee.springmvc.reststudents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//        WebClient webClient = WebClient.create();
//        Student student = webClient.get()
//                .uri("http://localhost:8080/students/1")
//                .retrieve()
//                .bodyToMono(Student.class)
//                .block();
//    }
    @Bean
    public CommandLineRunner printAllBeans(ApplicationContext applicationContext) {
        return args -> {
            Arrays.stream(applicationContext.getBeanDefinitionNames())
                    .forEach(System.out::println);
        };
    }
}
