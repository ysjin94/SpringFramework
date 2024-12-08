package hello.core.beanFind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameBeanFindConfig.class);

    @Configuration
    static class SameBeanFindConfig {
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("An error occurs when multiple instances of the same type are found during a lookup.")
    void findBeanByTypeDuplicate(){
//        MemberRepository bean = context.getBean(MemberRepository.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("If multiple instances of the same type are found during a lookup, specifying a bean name resolves the issue")
    void findBeanByName(){
        MemberRepository bean = context.getBean("memberRepository1", MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("Find All bean by Type")
    void findBeanByType(){
        Map<String, MemberRepository> beansOfType = context.getBeansOfType(MemberRepository.class);
        for (String key: beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println(beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
