package fr.uge.jee.annotations.onlineshope;

import org.springframework.stereotype.Component;

@Component

public class DeliveryDrone implements Delivery{
    @Override
    public String description() {
        return "Delivery by drone";
    }
}
