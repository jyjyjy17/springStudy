package hello.springmvc.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 전체 parameter 조회
        req.getParameterNames().asIterator().forEachRemaining(name-> System.out.println("param = " + name));
        //2.특정 param 조회

        String age = req.getParameter("age");
        System.out.println("age = " + age);

        //3. 하나의 param에 여러 값이 있을 때
        String[] usernames = req.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }
        resp.getWriter().write("bye");
    }
}
