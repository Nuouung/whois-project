package avengers.whois.domain.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "corporation")
public class CorporateMember {
    @Id
    @Column(name = "CORP_ID")
    private String email;
    @Column(nullable = false)
    private String password;

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

    @Column(nullable = false)
    private char memberType; // 0110 추가 | C(기업) or W(개인)

    // img
    @Column(nullable = true)
    private String fname; // 업로드된 파일이름 (새이름)

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
