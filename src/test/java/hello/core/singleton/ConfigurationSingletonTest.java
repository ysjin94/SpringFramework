package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("AppConfig class: " + ac.getBean(AppConfig.class).getClass());

        //given
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        //when
        MemberRepository memoryMemberRepository1 = memberService.getMemberRepository();
        MemberRepository memoryMemberRepository2 = orderService.getMemberRepository();

        //then
        System.out.println("memberService => memoryMemberRepository1=>" + memoryMemberRepository1); ;
        System.out.println("orderService =>memoryMemberRepository2=>" + memoryMemberRepository2); ;
        System.out.println("memberRepository => " + memberRepository); ;

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean =" + bean.getClass());
    }
}
