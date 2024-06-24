package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    /*@PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";

        }
        Member member = loginService.login(form.getLoginId(), form.getPassword());
        if (member == null) {
            bindingResult.reject("loginFail", "id 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }
        //login 성공
        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }*/
    /*@PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";

        }
        Member member = loginService.login(form.getLoginId(), form.getPassword());
        if (member == null) {
            bindingResult.reject("loginFail", "id 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }
        //login 성공
        sessionManager.createSession(member,response);
        return "redirect:/";
    }*/
    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";

        }
        Member member = loginService.login(form.getLoginId(), form.getPassword());
        if (member == null) {
            bindingResult.reject("loginFail", "id 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }
        //login 성공 > session 있으면 반환, 없으면 신규 생성
        request.getSession().setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/";
    }

    /*@PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expiredCookie(response);
        return "redirect:/";
    }*/
    /*@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request, HttpServletResponse response) {
        sessionManager.expire(request);
        return "redirect:/";
    }
*/
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request, HttpServletResponse response) {
        //request.getSession(false).removeAttribute(SessionConst.LOGIN_MEMBER);
        request.getSession(false).invalidate();// session 과 그 안의 데이터 날리기
        return "redirect:/";
    }

    private static void expiredCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("memberId", null);

        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
