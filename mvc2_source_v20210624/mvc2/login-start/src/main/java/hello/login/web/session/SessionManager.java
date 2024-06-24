package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NANE = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();


    public void createSession(Object value, HttpServletResponse response){
        //session id 생성 그리고 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie cookie = new Cookie(SESSION_COOKIE_NANE, sessionId);
        response.addCookie(cookie);
    }

    public Object getSession(HttpServletRequest request) {

        Cookie cookie = findCookie(request, SESSION_COOKIE_NANE);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(cookieName)).findFirst().orElse(null);
    }

    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NANE);
        if (cookie != null) {
            sessionStore.remove(cookie.getValue());
        }
    }

}
