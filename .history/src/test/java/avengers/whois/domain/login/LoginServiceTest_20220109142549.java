package avengers.whois.domain.login;

import avengers.whois.domain.login.LoginConst;
import avengers.whois.domain.login.LoginService;
import avengers.whois.domain.member.CorporateMember;
import avengers.whois.domain.member.Member;
import avengers.whois.domain.member.WorkerMember;
import avengers.whois.web.member.LoginDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LoginServiceTest {

    @Autowired EntityManager em;
    @Autowired LoginService loginService;

    @BeforeEach
    void 테스트수행전_실행메소드() {
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

    @AfterEach
    void 테스트수행후_실행메소드() {

    }

    @Test
    void 유효개인회원_테스트() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_WORKER);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        WorkerMember workerMember = (WorkerMember) loginMap.get(LoginConst.MEMBER_WORKER);

        // then
        assertThat(loginMap.containsKey(LoginConst.MEMBER_WORKER)).isTrue();
        assertThat(workerMember).isInstanceOf(WorkerMember.class);
    }

    @Test
    void 유효기업회원_테스트() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name2@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_CORPORATION);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        CorporateMember corporateMember = (CorporateMember) loginMap.get(LoginConst.MEMBER_CORPORATION);

        // then
        assertThat(loginMap.containsKey(LoginConst.MEMBER_CORPORATION)).isTrue();
        assertThat(corporateMember).isInstanceOf(Member.class);
    }

    @Test
    void 틀린개인회원_테스트_아이디() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("xxxx@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_WORKER);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        WorkerMember workerMember = (WorkerMember) loginMap.get(LoginConst.MEMBER_WORKER);

        // then
        assertThat(loginMap.containsKey(LoginConst.LOGIN_FAIL)).isTrue();
        assertThat(workerMember).isNull();
    }

    @Test
    void 틀린개인회원_테스트_비밀번호() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name@mail.com");
        correctWorker.setPassword("hahaIamWrong");
        correctWorker.setMemberType(LoginConst.MEMBER_WORKER);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        WorkerMember workerMember = (WorkerMember) loginMap.get(LoginConst.MEMBER_WORKER);

        // then
        assertThat(loginMap.containsKey(LoginConst.LOGIN_FAIL)).isTrue();
        assertThat(workerMember).isNull();
    }

    @Test
    void 틀린기업회원_테스트_아이디() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("xxxx@mail.com");
        correctWorker.setPassword("1234hello");
        correctWorker.setMemberType(LoginConst.MEMBER_CORPORATION);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        CorporateMember corporateMember = (CorporateMember) loginMap.get(LoginConst.MEMBER_CORPORATION);

        // then
        assertThat(loginMap.containsKey(LoginConst.LOGIN_FAIL)).isTrue();
        assertThat(corporateMember).isNull();
    }

    @Test
    void 틀린기업회원_테스트_비밀번호() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name2@mail.com");
        correctWorker.setPassword("틀린거임");
        correctWorker.setMemberType(LoginConst.MEMBER_CORPORATION);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());
        CorporateMember corporateMember = (CorporateMember) loginMap.get(LoginConst.MEMBER_CORPORATION);

        // then
        assertThat(loginMap.containsKey(LoginConst.LOGIN_FAIL)).isTrue();
        assertThat(corporateMember).isNull();
    }

    @Test
    void 악의적인접근테스트() {
        // given
        LoginDto correctWorker = new LoginDto();
        correctWorker.setEmail("name2@mail.com");
        correctWorker.setPassword("abcd");
        correctWorker.setMemberType(null);

        // when
        Map<String, Member> loginMap = loginService.login(correctWorker.getEmail(), correctWorker.getPassword(), correctWorker.getMemberType());

        // then
        assertThat(loginMap.containsKey(LoginConst.LOGIN_FAIL)).isTrue();
        assertThat(loginMap.get(LoginConst.LOGIN_FAIL)).isNull();
    }

}