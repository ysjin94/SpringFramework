package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("DI Container without Spring")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. inquiry : whenever call method, create it.
        MemberService memberService1 = appConfig.memberService();

        //2. inquiry : whenever call method, create it.
        MemberService memberService2 = appConfig.memberService();

        //Check it
        System.out.println("memberService1 =" + memberService1);
        System.out.println("memberService2 =" + memberService2);

        //memberServce1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("Applied Singleton")
    void singletonService(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        //Check it
        System.out.println("instance1 =" + instance1);
        System.out.println("instance2 =" + instance2);

        //instance1 != instance2
        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("Spring Container & Singleton")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. inquiry : whenever call method, create it.
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        //2. inquiry : whenever call method, create it.
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //Check it
        System.out.println("memberService1 =" + memberService1);
        System.out.println("memberService2 =" + memberService2);

        //memberServce1 != memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
