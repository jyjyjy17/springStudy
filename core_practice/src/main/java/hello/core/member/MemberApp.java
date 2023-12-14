package hello.core.member;

import hello.core.AppConfig;
import hello.core.CoreApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        CoreApplication.AppConfig appConfig = new CoreApplication.AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member"+memberA.getName());
        System.out.println("found member"+findMember.getName());
    }
}
