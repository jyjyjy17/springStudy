package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototype {

    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class,ClientBean.class);
        ClientBean bean = ac.getBean(ClientBean.class);
        int logic = bean.logic();
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int logic2 = bean.logic();
        Assertions.assertThat(logic).isNotEqualTo(logic2);

    }
    @RequiredArgsConstructor
    static class ClientBean{
        private final PrototypeBean prototypeBean;
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;


        public int logic(){
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count;
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init"+this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.end"+this);
        }
    }

}
