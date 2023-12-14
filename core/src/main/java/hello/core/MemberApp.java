package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//appconfig 환경 설정 정보를 갖고 spring이 springbean에 객체 생성한 걸 다 집어 넣어서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//이젠 스프링 컨테이너를 통해서 찾아온다. name은 내가 찾을 객체의 이름인데 기본적으로 method name이다. 2번째 인자는 반환타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember"+findMember.getName());
        System.out.println("member"+member.getName());
    }
}
