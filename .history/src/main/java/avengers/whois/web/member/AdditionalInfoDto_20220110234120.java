package avengers.whois.web.member;

import avengers.whois.domain.member.CorporateMember;
import avengers.whois.domain.member.NewMember;
import avengers.whois.domain.member.WorkerMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdditionalInfoDto {
    private long ono;

    // 아래 4개 데이터는 기업입장에서는 선호하는~~, 개인입장에서는 내 간단이력~~
    private String prefJob; // prefered job 직무
    private String prefMajor; // prefered major 전공
    private String prefExp; // prefered experience 경력정보 (신입 혹은 경력 여부, 타입은 추가논의 후 확정)
    private int expMonths; // 경력직인 경우, 경력을 개월수 단위로 (자동계산해)저장. 사용자3년1개월입력 > 테이블엔 37 저장

    NewMember newMember;
}
