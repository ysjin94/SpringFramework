package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {


    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int cnt1 = clientBean1.logic();
        Assertions.assertThat(cnt1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int cnt2 = clientBean2.logic();
        Assertions.assertThat(cnt2).isEqualTo(1);
    }
//    @Scope("singleton")
//    static class ClientBean{
//        private final PrototypeBean bean;
//
//        @Autowired
//        public ClientBean(PrototypeBean bean) {
//            this.bean = bean;
//        }
//
//        public int logic(){
//            bean.addCount();
//            return bean.getCount();
//
//        }
//    }


//    @Scope("singleton")
//    static class ClientBean{
//
//        @Autowired
//        private ObjectProvider<PrototypeBean> beanProvider;
//
//        public int logic(){
//            PrototypeBean prototypeBean = beanProvider.getObject();
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> beanProvider;

        public int logic(){
            PrototypeBean prototypeBean = beanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init :" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
