package avengers.whois.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String prefJob; // prefered job 직무
    private String prefMajor; // prefered major 전공
    private String prefExp; // prefered experience 경력정보 (신입 혹은 경력 여부, 타입은 추가논의 후 확정)
    private int expYears; // 경력직인 경우, 경력을 개월수 단위로 (자동계산해)저장.

}
