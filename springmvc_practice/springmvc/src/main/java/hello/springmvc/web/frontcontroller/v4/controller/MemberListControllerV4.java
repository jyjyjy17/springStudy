package hello.springmvc.web.frontcontroller.v4.controller;

import hello.springmvc.domain.member.Member;
import hello.springmvc.domain.member.MemberRepository;
import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        System.out.println("MvcMemberListServlet.service");
        List<Member> members = memberRepository.findAll();

        String viewPath = "members";
        model.put("members", members);
        return viewPath;
    }
}