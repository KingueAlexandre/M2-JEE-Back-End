package fr.uge.jee.annotations.onlineshope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeliveryClassic implements Delivery{
    private final int delay;

    public DeliveryClassic(@Value("${onlineshop.deliveryclassic.delay}") int delay){
        this.delay = delay;
    }
    @Override
    public String description() {
        return "Standard Delivery with a delay of " + delay + " days";
    }
}
