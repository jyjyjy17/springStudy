package hello.springmvc.web.frontcontroller.v2.controller;

import hello.springmvc.web.frontcontroller.MyView;
import hello.springmvc.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//view로 옮겨가자~
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //dispatcher로 옮겨주자
        return new MyView(viewPath);
    }
}
