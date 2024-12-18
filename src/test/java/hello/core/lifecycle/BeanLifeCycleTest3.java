package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest3 {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        NetworkClient3 client = context.getBean(NetworkClient3.class);
        context.close();
    }

    @Configuration
    static class lifeCycleConfig {
        @Bean
        public NetworkClient3 networkClient() {
            NetworkClient3 networkClient = new NetworkClient3();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
