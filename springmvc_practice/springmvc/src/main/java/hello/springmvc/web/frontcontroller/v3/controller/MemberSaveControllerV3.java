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
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        String path = "save-result";
        ModelView modelView = new ModelView(path);
        modelView.getModel().put("member", member);
        return modelView;
    }
}