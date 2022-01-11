package avengers.whois.domain.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {

    public Optional<AdditionalInfo> findByNewMember_Email(String id);


    /** 이하는 매칭 관련 로직. 회원 -> 기업 / 기업 -> 회원 모두 가능하다.
     *  매개변수 첫번째는 meberType, 두번째는 기업 -> 회원일경우 기업의 pref 정보 , 회원 -> 기업일경우 회원의 pref 정보를 의미한다.
     *  ex) '기업'이 선호하는 '직무'를 가진 '회원'을 찾는다. (기업 -> 회원) // findMemberWithPrefJob('W', (기업의 선호 직무));
     */
    // prefJob과 매치되는 인재 리스트 추출
    // 있으면 isPresent == true, 없으면 isEmpty == true
    @Query("select a.newMember from AdditionalInfo a where a.newMember.memberType = :memberType and a.prefJob = :prefJob")
    List<NewMember> findMemberWithPrefJob(@Param("memberType") char memberType, @Param("prefJob") String prefJob);

    // prefMajor과 매치되는 인재 리스트 추출
    // 있으면 isPresent == true, 없으면 isEmpty == true
    @Query("select a.newMember from AdditionalInfo a where a.newMember.memberType = :memberType and a.prefMajor = :prefMajor")
    List<NewMember> findWorkerWithPrefMajor(@Param("memberType") char memberType, @Param("prefMajor") String prefMajor);

    // prefExp와 매치되는 인재 리스트 추출
    // 있으면 isPresent == true, 없으면 isEmpty == true
    @Query("select a.newMember from AdditionalInfo a where a.newMember.memberType = :memberType and a.prefExp = :prefExp")
    List<NewMember> findWorkerWithPrefExp(@Param("memberType") char memberType, @Param("prefExp") String prefExp);

    // expMonths보다 경력이 높은 인재 리스트 추출
    // ex) 기업의 선호 경력차 개월 수 36개월. 그럼 36개월 이상의 인재만 보이도록 함!
    // 있으면 isPresent == true, 없으면 isEmpty == true
    @Query("select a.newMember from AdditionalInfo a where a.newMember.memberType = :memberType and a.expMonths > :expMonths")
    List<NewMember> findWorkerWithExpMonths(@Param("memberType") char memberType, @Param("expMonths") int expMonths);
}
