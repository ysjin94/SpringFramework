package hello.core.singleton;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleTon() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService service1 = ac.getBean(StatefulService.class);
        StatefulService service2 = ac.getBean(StatefulService.class);

        //ThreadA : UserA order 20 dollars
        int userAPrice = service1.order("UserA", 10);

        //ThreadB : UserB order 20 dollars
        int userBPrice = service2.order("UserB", 20);

        //ThreadA : UserA inquiry order price
//        int price = service1.getPrice();
        System.out.println("price: " + userAPrice);

//        org.assertj.core.api.Assertions.assertThat(price).isEqualTo(10);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}