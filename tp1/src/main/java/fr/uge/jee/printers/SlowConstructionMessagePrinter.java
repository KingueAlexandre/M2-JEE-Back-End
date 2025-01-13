package fr.uge.jee.printers;

public class SlowConstructionMessagePrinter implements MessagePrinter{

    public SlowConstructionMessagePrinter() throws InterruptedException {
        System.out.println("SLEEP!");
        Thread.sleep(5000);
        System.out.println("??");
    }

    @Override
    public void printMessage() {
        System.out.println("message of SlowConstructionMessagePrinter");

    }
}
