package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InsuranceReturn implements Insurance{
    private final boolean privatE;

    public InsuranceReturn(@Value("${onlineshop.insurancereturn.private}") boolean privee ){
        this.privatE = privee;
    }
    @Override
    public String description() {
        if(!privatE){
            return "Return insurance only for members";
        }
        return "Return open to all";
    }
}
