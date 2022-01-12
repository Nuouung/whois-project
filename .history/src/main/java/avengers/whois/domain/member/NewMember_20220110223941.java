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
@Table(name = "collate")
public class NewMember {
    // ** Member Type identifier (must not be null) **************
    @Column(nullable = false)
    private char memberType; // Jan.10 추가 | C(기업) or W(개인)

    // ** Commonly used columns (must not be null) **************
    @Id
    @Column(name = "member_id") // pk지정, 중복방지
    private String email;
    @Column(nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER) // role은 뭔가 반드시 이거로딩해야할 것 같은 기분
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @Column(nullable = true) // 사진은 나중에 올릴수 있으니 nullable
    private String fname; // image file (개인:사진 / 기업:대표사진)

    @Column(nullable = false)
    private String name; // 개인:회원이름 / 기업:담당자이름
    @Column(nullable = false)
    private String phoneNumber; // 개인:회원번호 / 기업:담당자번호
    @Column(nullable = false)
    private LocalDate birthDate;// 개인:생일 / 기업:설립일
    @Column(nullable = false)
    private String address; // 개인:주소 / 기업:사업자주소

    @CreatedDate
    private LocalDateTime createdDate; // 가입시간정보
    @LastModifiedDate
    private LocalDateTime modifiedDate; // 마지막회원정보수정시간정보

    // ** information for Worker Member ****************************
    @Column(nullable = false) // 요즘 무서운 시대라 성별체크는 nullable 해야하나 생각 들기 시작함
    private char gender;
    @Column(nullable = false)
    private boolean finding; // 구직여부 T:구함
    @Column(nullable = true) // 이력서는 가입할때 준비안된 경우가 더 많으니 nullable
    private String resume; // 이력서 파일이름 (새이름)

    // ** information for Corporate Member *************************
    // 어차피 얘들은 회원가입하면서 api로 강제 not null 될놈들
    @Column(nullable = false)
    private String corpNo; // 사업자번호
    @Column(nullable = false)
    private String corpName; // 사업자명
    @Column(nullable = false)
    private String industryField; // 업종
}
