package fr.uge.jee.onlineshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-online.xml");
        OnlineShop onlineShop = context.getBean("onlineshop",fr.uge.jee.onlineshop.OnlineShop.class);
        onlineShop.printDescription();
    }
}
