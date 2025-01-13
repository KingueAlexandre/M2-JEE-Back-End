package fr.uge.jee.printers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        System.out.println("ok");
        ApplicationContext context = new ClassPathXmlApplicationContext("config-printers.xml");
        var printers = context.getBean("simpleMessage",fr.uge.jee.printers.MessagePrinter.class);
        printers.printMessage();
        var printerf = context.getBean("frenchMessage",fr.uge.jee.printers.MessagePrinter.class);
        printerf.printMessage();
        var printerc = context.getBean("custMessage",fr.uge.jee.printers.MessagePrinter.class);
        printerc.printMessage();
        //Quel constructeur est appelé ?
        //Celui sans paramètre.
        var printercA = context.getBean("custMessageWithArgs",fr.uge.jee.printers.MessagePrinter.class);
        printercA.printMessage();
        var printerco = context.getBean("countMessage",fr.uge.jee.printers.MessagePrinter.class);
        printerco.printMessage();
        printerco.printMessage();
        printerco.printMessage();
        var printerco2 = context.getBean("countMessage",fr.uge.jee.printers.MessagePrinter.class);
        printerco2.printMessage();
        printerco2.printMessage();
        var printersl = context.getBean("slowMessage",fr.uge.jee.printers.MessagePrinter.class);
        printersl.printMessage();
        //A quel moment sont instantiés les beans ?
        //Au moment de la création du ApplicationContext à partir du "config-printers.xml"
        //Quel est l'inconvéniant majeur de déférer l'instantition des beans ?
        //

    }
}


