package hello.springmvc.web.frontcontroller.v3;

import hello.springmvc.web.frontcontroller.ModelView;
import hello.springmvc.web.frontcontroller.MyView;
import hello.springmvc.web.frontcontroller.v3.ControllerV3;
import hello.springmvc.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.springmvc.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.springmvc.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap=new HashMap<>();

    FrontControllerServletV3(){
        controllerMap.put( "/front-controller/v3/members", new MemberListControllerV3());
        controllerMap.put( "/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put( "/front-controller/v3/members/new-form", new MemberFormControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerV3 controller = controllerMap.get(requestURI);
        System.out.println("controllerV3 = " + controller);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap 생성
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(name->paramMap.put(name,req.getParameter(name)));
        System.out.println("paramMap = " + paramMap);
        ModelView modelView = controller.process(paramMap);
        //viewResolver 필요
        String viewName = modelView.getViewName();
        MyView myView = this.viewResolver(viewName);
        System.out.println("myView = " + myView);
        myView.render(modelView.getModel(),req,resp);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
