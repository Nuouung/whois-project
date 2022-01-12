package avengers.whois.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class AdditionalInfoRepositoryTest {

    @Autowired NewMemberRepository memberRepository;
    @Autowired AdditionalInfoRepository additionalInfoRepository;

    @BeforeEach
    void beforeEach() {
        // 개인회원 한명
        NewMember worker = NewMember.builder().memberType('W').email("hello@jpa.com").password("1234")
                .roles(new HashSet<>()).name("worker").phoneNumber("1234").birthDate(LocalDate.now())
                .address("Korea").build();
        memberRepository.save(worker);

        // 기업회원 한명
        NewMember corporate = NewMember.builder().memberType('C').email("hello@spring.com").password("1234")
                .roles(new HashSet<>()).name("corporate").phoneNumber("1234").birthDate(LocalDate.now())
                .address("Korea").build();
        memberRepository.save(corporate);

        // 개인회원과 fk로 붙어 있는 추가정보(AdditionalInfo) 하나 & 기업회원과 fk로 붙어 있는 추가정보 하나
        AdditionalInfo workerInfo = AdditionalInfo.builder().prefJob("backend developer").prefMajor("computer science").prefExp("experienced").expMonths(36)
                .newMember(worker).build();
        additionalInfoRepository.save(workerInfo);

        AdditionalInfo corporateInfo = AdditionalInfo.builder().prefJob("backend developer").prefMajor("computer science").prefExp("experienced").expMonths(15)
                .newMember(corporate).build();
        additionalInfoRepository.save(corporateInfo);
    }

    @Test
    void prefJobTest() {
        // give
        NewMember corporate = memberRepository.findById("hello@spring.com").get();
        NewMember worker = memberRepository.findById("hello@jpa.com").get();
        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(corporate.getEmail()).get();

        // when
        List<NewMember> matchedWorker = additionalInfoRepository.findMemberWithPrefJob('W', corpAdditionalInfo.getPrefJob());

        // then
        Assertions.assertThat(matchedWorker.get(0).getEmail()).isEqualTo("hello@jpa.com");
    }

    @Test
    void prefJobEmptyTest() {
        // give
        NewMember worker = memberRepository.findById("hello@jpa.com").get();

        NewMember corporate = NewMember.builder().memberType('C').email("hello2@spring.com").password("1234")
                .roles(new HashSet<>()).name("corporate").phoneNumber("1234").birthDate(LocalDate.now())
                .address("Korea").build();
        memberRepository.save(corporate);

        AdditionalInfo corporateAdditionalInfo = AdditionalInfo.builder().prefJob("frontend developer").prefMajor("computer science").prefExp("newbie").expMonths(0)
                .newMember(corporate).build();
        additionalInfoRepository.save(corporateAdditionalInfo);

        // when
        List<NewMember> matchedWorker = additionalInfoRepository.findMemberWithPrefJob('W', corporateAdditionalInfo.getPrefJob());

        // then
        Assertions.assertThat(matchedWorker.isEmpty()).isTrue();
    }

    @Test
    void expMonthsTest() {
        // give
        NewMember corporate = memberRepository.findById("hello@spring.com").get();
        NewMember worker = memberRepository.findById("hello@jpa.com").get();

        NewMember worker2 = NewMember.builder().memberType('W').email("hello2@jpa.com").password("1234")
                .roles(new HashSet<>()).name("worker").phoneNumber("1234").birthDate(LocalDate.now())
                .address("Korea").build();
        memberRepository.save(worker2);

        AdditionalInfo worker2Info = AdditionalInfo.builder().prefJob("backend developer").prefMajor("computer science").prefExp("experienced").expMonths(10)
                .newMember(worker2).build();
        additionalInfoRepository.save(worker2Info);

        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(corporate.getEmail()).get();

        // when
        List<NewMember> matchedWorker = additionalInfoRepository.findWorkerWithExpMonths('W', corpAdditionalInfo.getExpMonths());

        // then
        Assertions.assertThat(matchedWorker.size()).isEqualTo(1);
    }

}