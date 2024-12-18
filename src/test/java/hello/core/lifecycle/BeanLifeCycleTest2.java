package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest2 {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        NetworkClient2 client = context.getBean(NetworkClient2.class);
        context.close();
    }

    @Configuration
    static class lifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod =  "close")
        public NetworkClient2 networkClient() {
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
