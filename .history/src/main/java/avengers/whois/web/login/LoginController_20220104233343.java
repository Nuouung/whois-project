package avengers.whois.web.login;

import avengers.whois.domain.login.LoginConst;
import avengers.whois.domain.login.LoginService;
import avengers.whois.web.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final HttpSession session;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new MemberDto());
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "member") MemberDto memberDto, BindingResult bindingResult) {
        Member foundMember = loginService.login(memberDto.getEmail(), memberDto.getPassword());

        // 인증
        if (foundMember == null) {
            // 로그인 실패
            bindingResult.addError(new ObjectError("MemberDto", "아이디나 비밀번호가 올바르지 않습니다"));
            return "/login/loginForm";
        }

        // 로그인 성공
        session.setAttribute(LoginConst.loginSession, new MemberDto(foundMember.getEmail(), foundMember.getPassword()));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        loginService.logout(session);
        return "redirect:/";
    }
}
