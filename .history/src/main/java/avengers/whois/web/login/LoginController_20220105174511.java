package avengers.whois.web.login;

import avengers.whois.domain.login.LoginConst;
import avengers.whois.domain.login.LoginService;
import avengers.whois.domain.member.CorporateMember;
import avengers.whois.domain.member.Member;
import avengers.whois.domain.member.WorkerMember;
import avengers.whois.web.member.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final HttpSession session;

    @PostMapping("/")
    public String login(@ModelAttribute(name = "member") LoginDto loginDto, BindingResult bindingResult) {
        System.out.println(loginDto.getMemberType());
        // 경우의 수 :
        // 1) 개인회원 <LoginConst.MEMBER_WORKER, Member>
        // 2) 기업회원 <LoginConst.MEMBER_CORPORATION, Member>
        // 3) 로그인 실패 <LoginConst.LOGIN_FAIL, null>
        Map<String, Member> loginMap = loginService.login(loginDto.getEmail(), loginDto.getPassword(),
                loginDto.getMemberType());

        // 인증
        if (loginMap.containsKey(LoginConst.LOGIN_FAIL)) {
            // 로그인 실패
            bindingResult.addError(new ObjectError("MemberDto", "아이디나 비밀번호가 올바르지 않습니다"));
            // 다시 로그인 화면으로 보내고, 에러메시지를 같이 띄워 보냄
            return "home";
        }

        // 로그인 성공
        // 아래 로직을 타면 세션이 만들어진다.
        createSession(loginMap);

        return "redirect:/memberInfo"; // TODO 기업/개인 해당 main 화면으로 가야 함!
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        loginService.logout(session);
        return "redirect:/";
    }

    private void createSession(Map<String, Member> loginMap) {
        if (loginMap.containsKey(LoginConst.MEMBER_WORKER)) {
            WorkerMember member = (WorkerMember) loginMap.get(LoginConst.MEMBER_WORKER);
            createSession(member.getEmail(), member.getPassword());
        } else if (loginMap.containsKey(LoginConst.MEMBER_CORPORATION)) {
            CorporateMember member = (CorporateMember) loginMap.get(LoginConst.MEMBER_CORPORATION);
            createSession(member.getEmail(), member.getPassword());
        }
    }

    private void createSession(String email, String password) {
        LoginDto sessionInfo = LoginDto.builder().email(email).password(password).build();
        session.setAttribute(LoginConst.loginSession, sessionInfo);
    }
}
