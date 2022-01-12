package avengers.whois.web.login;

import avengers.whois.domain.login.LoginConst;
import avengers.whois.domain.login.LoginService;
import avengers.whois.domain.member.CorporateMember;
import avengers.whois.domain.member.Member;
import avengers.whois.domain.member.WorkerMember;
import avengers.whois.web.member.LoginDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LoginControllerTest {

    @Autowired EntityManager em;
    @Autowired LoginService loginService;
    @Autowired HttpSession session;

    @BeforeEach
    void beforeEach() {
        Member worker = WorkerMember.builder()
                .email("name@mail.com")
                .password("1234hello")
                .name("dog")
                .phoneNumber("05044323333")
                .birthday(LocalDate.now())
                .gender('m')
                .finding(true)
                .address("bigHouse")
                .build();

        Member corporation = CorporateMember.builder()
                .email("name2@mail.com")
                .password("1234hello")
                .repName("cat")
                .repPhoneNumber("05044323333")
                .corpNo("1234")
                .corpName("맛나슈퍼배고파")
                .industryField("바다나라치킨공주")
                .corpAddress("1234")
                .establishedDate(LocalDate.now())
                .build();

        em.persist(worker);
        em.persist(corporation);
    }

    @Test
    void 개인세션테스트() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_WORKER);
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());

        // when
        createSession(loginMap);

        // then
        Assertions.assertThat(session.getAttribute(LoginConst.loginSession)).isInstanceOf(LoginDto.class);
    }

    @Test
    void 기업세션테스트() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name2@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_CORPORATION);
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());

        // when
        createSession(loginMap);

        // then
        Assertions.assertThat(session.getAttribute(LoginConst.loginSession)).isInstanceOf(LoginDto.class);
    }

    // controller 안에 있는 메소드가 프라이빗 메소드여서 그냥 가져왔다.
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