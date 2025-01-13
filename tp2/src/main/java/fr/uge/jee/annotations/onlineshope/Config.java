package fr.uge.jee.annotations.onlineshope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Set;

@Configuration
@ComponentScan
@PropertySource("classpath:onlineshop.properties")
public class Config {
//    @Bean
//    Delivery deliveryClassic(){
//        return new DeliveryClassic();
//    }
//
//    @Bean
//    Delivery deliveryDrone(){
//        return new DeliveryDrone();
//    }
//    @Bean
//    Insurance insuranceThief(){
//        return new InsuranceThief();
//    }
//    @Bean
//    Insurance insuranceReturnT(){
//        return new InsuranceReturn(true);
//    }
//    @Bean
//    Insurance insuranceReturnF(){
//        return new InsuranceReturn(false);
//    }


    //    @Bean
//    OnlineShop shop(){
//        return new OnlineShop("Amazon", Set.of(new DeliveryDrone(),new DeliveryClassic(5)), Set.of(new InsuranceReturn(false)));
//    }
//    @Bean
//    OnlineShop shop(){
//        return new OnlineShop();
//    }
}
