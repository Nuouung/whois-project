package avengers.whois.domain.member;

// import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
// import java.time.LocalDate;
// import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    EntityManager em;
    // @Autowired
    // CorporateMemberRepository corporateMemberRepository;
    // @Autowired
    // WorkerMemberRepository workerMemberRepository;

    @Test
    void jpa_객체상속_가능한가_ver1() {
        // given
        // WorkerMember workerMember = WorkerMember.builder()
        // .email("name@mail.com")
        // .password("1234hello")
        // .name("dog")
        // .phoneNumber("05044323333")
        // .birthday(LocalDate.now())
        // .gender('m')
        // .finding(true)
        // .address("bigHouse")
        // .build();

        // workerMemberRepository.save(workerMember);

        // // 영속성 컨텍스트 초기화
        // // 이 작업을 수행하면 이후 영속성 컨텍스트에서 값을 찾아오지 않고 db에서 값을 찾아온다.
        // em.flush();
        // em.clear();

        // // when
        // WorkerMember foundMember =
        // workerMemberRepository.findByEmail(workerMember.getEmail()).get();

        // // then
        // assertThat(foundMember.getName()).isEqualTo(workerMember.getName());
    }

    @Test
    void jpa_객체상속_가능한가_ver2() {
        // given
        // Member member = WorkerMember.builder()
        // .email("name@mail.com")
        // .password("1234hello")
        // .name("dog")
        // .phoneNumber("05044323333")
        // .birthday(LocalDate.now())
        // .gender('m')
        // .finding(true)
        // .address("bigHouse")
        // .build();

        // WorkerMember workerMember = (WorkerMember) member;
        // workerMemberRepository.save(workerMember);

        // // 영속성 컨텍스트 초기화
        // // 이 작업을 수행하면 이후 영속성 컨텍스트에서 값을 찾아오지 않고 db에서 값을 찾아온다.
        // em.flush();
        // em.clear();

        // // when
        // WorkerMember foundMember =
        // workerMemberRepository.findByEmail(workerMember.getEmail()).get();

        // // then
        // assertThat(foundMember.getName()).isEqualTo(workerMember.getName());
    }

}