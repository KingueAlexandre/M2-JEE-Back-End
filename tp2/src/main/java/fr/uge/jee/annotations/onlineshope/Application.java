package fr.uge.jee.annotations.onlineshope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ObjectInputFilter;

public class Application {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("config-online.xml");
//        OnlineShop onlineShop = context.getBean("onlineshop",fr.uge.jee.onlineshop.OnlineShop.class);
//        onlineShop.printDescription();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        var checker = applicationContext.getBean(OnlineShop.class);
        checker.printDescription();

    }
}
