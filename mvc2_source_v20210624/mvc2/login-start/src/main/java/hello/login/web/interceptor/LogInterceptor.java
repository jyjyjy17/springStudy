package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    public static final String LOG_ID = "logId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        //uuid를 공유하는 방법
        request.setAttribute(LOG_ID,uuid);

        //@RequestMapping: HandlerMethod
        //정적리소스: ResourceHttpRequestHandler
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            //handler method는 빈이름, return type 등 모든걸 get method로 출력 가능하다 .
        }
        log.info("log interceptor 시작 ");
        log.info("REQUEST [{}{}{}]", requestURI, uuid, handler);
        return true;

    }

}
