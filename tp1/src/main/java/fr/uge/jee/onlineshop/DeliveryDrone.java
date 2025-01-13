package fr.uge.jee.onlineshop;

public class DeliveryDrone implements Delivery{
    @Override
    public String description() {
        return "Delivery by drone";
    }
}
