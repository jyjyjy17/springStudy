package hello.servelet.web.frontcontroller.v3.controller;

import hello.servelet.domain.Member.Member;
import hello.servelet.domain.Member.MemberRepository;
import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.v3.ControllerV3;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
       //modelView에 저장
        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members",members);
        return modelView;

    }
}
