package hello.springmvc.web.frontcontroller.v3.controller;

import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.MyView;
import hello.springmvc.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
//view로 옮겨가자~
        String viewPath = "new-form";
        //dispatcher로 옮겨주자
        return new ModelView(viewPath);
    }
}
