package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDisountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("An error occurs if multiple instances of the same type are found during a lookup.")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = context.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,() -> context.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("If multiple instances of the same type are found during a lookup, specifying the bean name will resolve the issue.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy bean = context.getBean("rateDiscountPolicy",DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("Retrieve all instances of a specific type.")
    void findBeanBySubName(){
        RateDiscountPolicy bean = context.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("Retrieve all instances by the parent type.")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beans = context.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
        for (String key : beans.keySet()) {
            System.out.println("key = " + key + "value =" + beans.get(key));
        }
    }

    @Test
    @DisplayName("Retrieve all instances by the parent type - Object.")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" +
                    beansOfType.get(key));
        }
    }
}
