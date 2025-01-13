package fr.uge.jee.annotations.onlineshope;

import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OnlineShop {

    @Value("${onlineshop.onlineshop.name}")
    private String name;

    @Autowired
    private Set<Delivery> deliveryOptions;

    @Autowired
    private Set<Insurance> insurances;

//    public OnlineShop(@Value("Amazon") String name, Set<Delivery> deliveryOptions, Set<Insurance> insurances){
//        Objects.requireNonNull(deliveryOptions);
//        Objects.requireNonNull(insurances);
//        Objects.requireNonNull(name);
//        this.name = name;
//        this.deliveryOptions = deliveryOptions;
//        this.insurances = insurances;
//    }

    public void printDescription(){
        System.out.println(name);
        System.out.println("Delivery options");
        deliveryOptions.forEach(opt -> System.out.println("\t"+opt.description()));
        System.out.println("Insurance options");
        insurances.forEach(insurance -> System.out.println("\t"+insurance.description()));
    }
}