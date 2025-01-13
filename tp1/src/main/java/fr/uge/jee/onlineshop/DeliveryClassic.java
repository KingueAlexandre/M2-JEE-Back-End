package fr.uge.jee.onlineshop;

public class DeliveryClassic implements Delivery{
    private final int delay;

    public DeliveryClassic(int delay){
        this.delay = delay;
    }
    @Override
    public String description() {
        return "Standard Delivery with a delay of " + delay + " days";
    }
}
