package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Find Bean by Name")
    void findBeanByName() {
        MemberService memberService = context.getBean("memberService", MemberService.class);
        System.out.println(memberService);
        System.out.println(memberService.getClass());

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Find Bean by Type")
    void findBeanByType() {
        MemberService memberService = context.getBean(MemberService.class);
        System.out.println(memberService);
        System.out.println(memberService.getClass());

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = context.getBean("memberService", MemberServiceImpl.class);
        System.out.println(memberService);
        System.out.println(memberService.getClass());

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("Fail to find bean by name")
    void findBeanByNameFail() {
//        MemberService memberService = context.getBean("xxxxx", MemberService.class);
//        System.out.println(memberService);
//        System.out.println(memberService.getClass());
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("xxxxxx", MemberService.class));
    }

}
