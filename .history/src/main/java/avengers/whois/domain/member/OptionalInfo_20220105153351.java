package avengers.whois.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "additionalInformations")
@EntityListeners(AuditingEntityListener.class)
public class OptionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADD_ID")
    private long ono;

    // 아래 4개 데이터는 기업입장에서는 선호하는~~, 개인입장에서는 내 간단이력~~
    @Column(nullable = true)
    private String prefJob; // prefered job 직무
    @Column(nullable = true)
    private String prefMajor; // prefered major 전공
    @Column(nullable = true)
    private String prefExp; // prefered experience 경력정보 (신입 혹은 경력 여부, 타입은 추가논의 후 확정)
    @Column(nullable = true)
    private int expYears; // 경력직인 경우, 경력을 개월수 단위로 (자동계산해)저장. 사용자3년1개월입력 > 테이블엔 37 저장

    @OneToOne(optional = true)
    @JoinColumn(name = "Corp_id")
    CorporateMember corporateMember;
}
