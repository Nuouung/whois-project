package avengers.whois.domain.login;

import avengers.whois.domain.member.Member;
import avengers.whois.domain.member.MemberRepository;
import avengers.whois.web.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     *  로그인
     */
    public Member login(String email, String password) {
        Optional<Member> foundMember = memberRepository.findByEmail(email);

        if (foundMember.isPresent()) {
            Member member = foundMember.get();
            if (member.getPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }

    /**
     *  로그아웃
     */
    public void logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute(LoginConst.loginSession);
            session.invalidate();
        }
    }
}
