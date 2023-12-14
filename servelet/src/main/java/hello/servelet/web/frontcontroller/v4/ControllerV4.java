package hello.servelet.web.frontcontroller.v4;

import hello.servelet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {
    /**
     *
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String,String> paramMap, Map<String,Object> model);

}
