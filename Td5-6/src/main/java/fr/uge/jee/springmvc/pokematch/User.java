package fr.uge.jee.springmvc.pokematch;

import jakarta.validation.constraints.Pattern;

public record User(@Pattern(regexp = "^[a-zA-Z\\s]+$",message = "Only lower and uppercase") String firstName,@Pattern(regexp = "^[a-zA-Z\\s]+$",message = "Only lower and uppercase") String lastName) {
    @Override
        public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
