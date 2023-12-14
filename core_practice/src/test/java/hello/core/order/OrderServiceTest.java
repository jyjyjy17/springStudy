package hello.core.order;

import hello.core.AppConfig;
import hello.core.CoreApplication;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    MemberService memberService ;
    OrderService orderService ;
    Member member = new Member(1L,"JYP", Grade.VIP);

    @BeforeEach
    public void beforeEach(){
//        CoreApplication.AppConfig appConfig = new CoreApplication.AppConfig();
//        memberService = appConfig.memberService();
//        orderService = appConfig.orderService();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = ac.getBean("memberService",MemberService.class);
        orderService = ac.getBean("orderService", OrderService.class);

    }

    @Test
    void order(){
        memberService.join(member);
        Order notebook = orderService.createOrder(member.getId(), "노트북", 1900000);
        notebook.getDiscountPrice();
        Assertions.assertThat(notebook.getDiscountPrice()).isEqualTo(1000);


    }

    public static class RateDiscountPolicyTest {
    }
}
