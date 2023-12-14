package hello.servelet.web.frontcontroller.v4;

import hello.servelet.web.frontcontroller.MyView;
import hello.servelet.web.frontcontroller.v3.ControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servelet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servelet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servelet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet{
    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //req Uri
        String requestURI = req.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //parameter 다 받기
        Map<String,String> paramNames = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName->paramNames.put(paramName,req.getParameter(paramName)));
        Map<String, Object> model = new HashMap<>();
        //parameter controller에 전달
        String viewname = controller.process(paramNames, model);
        MyView view = viewResolvedr(viewname);
        view.render(model,req,res);

    }
    private static MyView viewResolvedr(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
