package avengers.whois.domain.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "corporation")
@EntityListeners(AuditingEntityListener.class)
public class CorporateMember implements Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CORP_ID")
    private long id;

    @Column(nullable = false)
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
    private List<String> roles = new ArrayList<>();

    // img
    @Column(nullable = true)
    private String fname; // 업로드된 파일이름 (새이름)

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
