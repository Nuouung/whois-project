package avengers.whois.domain.member;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "collate")
public class NewMember {
    // ** Commonly used columns (must not be null)
    @Id
    @Column(name = "member_id")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String fname; // image file (개인:사진 / 기업:대표사진)

    @Column(nullable = false)
    private String repName; // 담당자명
    @Column(nullable = false)
    private String repPhoneNumber; // 담당자번호

    @Column(nullable = false)
    private String corpNo; // 사업자번호
    @Column(nullable = false)
    private String corpName; // 사업자명
    @Column(nullable = false)
    private String industryField; // 업종
    @Column(nullable = false)
    private String corpAddress; // 사업자주소
    @Column(nullable = false)
    private LocalDate establishedDate;// 설립일

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

}
