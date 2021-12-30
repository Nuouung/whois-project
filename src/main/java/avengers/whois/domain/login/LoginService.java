package avengers.whois.domain.login;

import avengers.whois.domain.member.Member;
import avengers.whois.domain.member.MemberRepository;
import avengers.whois.web.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    /**
     *  로그인
     */
    public void login(String email, String password, MemberDto memberDto) {
        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null || !member.getPassword().equals(password)) {
            // 로그인 실패 TODO
        }

        // 로그인 성공
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        session.setAttribute(LoginConst.loginSession, memberDto);

    }

    /**
     *  로그아웃
     */
    public void logout() {
        session.removeAttribute(LoginConst.loginSession);
    }
}
