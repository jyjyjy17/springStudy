package hello.springmvc.web.frontcontroller.v4.controller;

import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object>Model) {
//view로 옮겨가자~
        String viewPath = "new-form";
        //dispatcher로 옮겨주자
        return viewPath;
    }
}
