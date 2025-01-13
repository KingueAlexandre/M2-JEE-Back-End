package fr.uge.jee.bookstore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-bookstore.xml");
        Library bookstore = context.getBean("library", Library.class);
        System.out.println(bookstore);
    }
}

//Utilisez autowire="constructor" pour définir le bean associé à Library en pensant bien à supprimer les arguments!
//Qu'observez vous ?
//ApplicationContext s'est servi parmi les beans du config (il a pris 3 beans contre 2 avant)