package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = context.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
