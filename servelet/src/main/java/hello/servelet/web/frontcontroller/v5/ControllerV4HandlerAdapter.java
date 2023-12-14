package hello.servelet.web.frontcontroller.v5;

import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.v4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String,Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        //adapter의 역할: 무조건 같은 ModelView를 반환하게 만들어주자
        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);
        return modelView;
    }
    private Map<String,String> createParamMap(HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName,request.getParameter(paramName)));
        return paramMap;
    }
}
