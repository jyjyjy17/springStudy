package hello.servelet.web.frontcontroller.v3.controller;

import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.MyView;
import hello.servelet.web.frontcontroller.v2.ControllerV2;
import hello.servelet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // FORM에는 저장하는 VALUE가 없습니다
        // 단지 VIEW만 저장하면 됩니다
        // new-form을 켜주는 역할입니다
        ModelView modelView = new ModelView("new-form");
        return modelView;
    }
}
