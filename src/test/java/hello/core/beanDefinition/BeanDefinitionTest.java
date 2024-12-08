package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("Check the bean configuration metadata.")
    void findApplicationBean(){
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(definitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("definitionName = " + definitionName + " beanDefinition = " + beanDefinition); ;
            }
        }
    }
}
