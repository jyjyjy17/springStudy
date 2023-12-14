package hello.core.scan;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {
    @Test
    void basicSacn() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConfigTest.class);
        MemberService memberService = ac.getBean(MemberService.class);

    }
}
