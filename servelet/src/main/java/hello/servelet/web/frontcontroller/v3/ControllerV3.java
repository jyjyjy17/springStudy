package hello.servelet.web.frontcontroller.v3;

import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.MyView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);

}
