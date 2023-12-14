package hello.servelet.web.springmvc.v3;

import hello.servelet.domain.Member.Member;
import hello.servelet.domain.Member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    //annotation 기반은 인터페이스로 설계 돼 있으므로 굉장히 유연하다. modelview를 직접 반환해도 되고 string 반환해도 됨.

    @GetMapping("/new-form")
    public String newform() {
        return  "new-form";
    }
    //request , response로 안 넘기고 바로 param을 받을 수도 있다.
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        //논리적 path 저장, model에 객체 저장
        model.addAttribute("member",member);
        return "save-result";
    }

    @GetMapping("")
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
