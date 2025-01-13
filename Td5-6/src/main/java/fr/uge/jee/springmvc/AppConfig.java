package fr.uge.jee.springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:pokemon.properties")
public class AppConfig { }
