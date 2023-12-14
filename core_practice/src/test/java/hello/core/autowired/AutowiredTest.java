package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption(){


        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }


    static class TestBean{
        //method 주입
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("nobean1 = "+noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean1){
            System.out.println("nobean2 = "+noBean1);
        }
        @Autowired
        public void setNoBean3(Optional<Member> noBean1){
            System.out.println("nobean3 = "+noBean1);
        }
    }
}
