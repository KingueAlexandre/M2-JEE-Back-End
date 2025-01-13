package fr.uge.jee.annotations.onlineshope;

import org.springframework.stereotype.Component;

@Component
public class InsuranceThief implements Insurance{
    @Override
    public String description() {
        return "Thief insurance";
    }
}
