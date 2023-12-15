package hello.springmvc.web.frontcontroller.v3.controller;

import hello.springmvc.domain.member.Member;
import hello.springmvc.domain.member.MemberRepository;
import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.MyView;
import hello.springmvc.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        System.out.println("MvcMemberListServlet.service");
        List<Member> members = memberRepository.findAll();

        String viewPath = "members";
        ModelView modelView = new ModelView(viewPath);
        modelView.getModel().put("members",members);
        return modelView;
    }
}