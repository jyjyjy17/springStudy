package hello.springmvc.web.frontcontroller.v3;

import hello.springmvc.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
