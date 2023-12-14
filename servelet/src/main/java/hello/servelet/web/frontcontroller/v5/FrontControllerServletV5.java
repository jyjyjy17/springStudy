package hello.servelet.web.frontcontroller.v5;

import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.MyView;
import hello.servelet.web.frontcontroller.v3.ControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servelet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servelet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servelet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servelet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import org.eclipse.jdt.internal.compiler.codegen.ObjectCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {

        handlerAdapterList.add(new ControllerV3HandlerAdapter());
        handlerAdapterList.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //handler = controller
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //handler를 처리할 adapter 찾기
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView modelview = adapter.handle(request,response,handler);

        String viewName = modelview.getViewName();
        MyView myView = viewResolvedr(viewName);
        myView.render(modelview.getModel(),request,response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if(adapter.support(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 못 찾겠다;;");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //controller 찾기
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }

    private static MyView viewResolvedr(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
