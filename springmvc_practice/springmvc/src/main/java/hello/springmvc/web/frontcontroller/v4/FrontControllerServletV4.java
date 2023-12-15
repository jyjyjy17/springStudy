package hello.springmvc.web.frontcontroller.v4;

import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.MyView;
import hello.springmvc.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.springmvc.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.springmvc.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap=new HashMap<>();

    FrontControllerServletV4(){
        controllerMap.put( "/front-controller/v4/members", new MemberListControllerV4());
        controllerMap.put( "/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put( "/front-controller/v4/members/new-form", new MemberFormControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerV4 controller = controllerMap.get(requestURI);
        System.out.println("controllerV4 = " + controller);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap 생성
        Map<String, String> paramMap = getParamMap(req);
        Map<String, Object> model = new HashMap<>();
        System.out.println("paramMap = " + paramMap);
        String viewName = controller.process(paramMap,model);
        //viewResolver 필요
        MyView myView = this.viewResolver(viewName);
        System.out.println("myView = " + myView);
        myView.render(model,req,resp);
    }

    private static Map<String, String> getParamMap(HttpServletRequest req) {
        HashMap<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(name-> paramMap.put(name, req.getParameter(name)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
