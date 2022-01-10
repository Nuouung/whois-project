package avengers.whois.SecurityThings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import avengers.whois.domain.member.CorporateMember;
import avengers.whois.domain.member.NewMemberDto;
import avengers.whois.domain.member.WorkerMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecureDTO extends User {
    public SecureDTO(NewMemberDto data) {
        super(data.getEmail(),
                data.getPassword(),
                data.getRoles()
                        .stream().map(content -> new SimpleGrantedAuthority(content))
                        .collect(Collectors.toSet()));
    }

    private char memberType; // Jan.10 추가 | C(기업) or W(개인)

    // User class에 있는 variable인 username, password, authorities 로 대치해 사용
    // private String email;
    // private String password;
    // private Set<String> roles = new HashSet<>();
    // 위 3개는 parent 인 Uer가 이미 가지고있음

    // ** Commonly used columns (must not be null) **************
    private String fname; // image file (개인:사진 / 기업:대표사진)

    private String name; // 개인:회원이름 / 기업:담당자이름
    private String phoneNumber; // 개인:회원번호 / 기업:담당자번호
    private LocalDate birthDate;// 개인:생일 / 기업:설립일
    private String address; // 개인:주소 / 기업:사업자주소

    private LocalDateTime createdDate; // 가입시간정보
    private LocalDateTime modifiedDate; // 마지막회원정보수정시간정보

    // ** information for Worker Member ****************************
    private char gender;
    private boolean finding; // 구직여부 T:구함
    private String resume; // 이력서 파일이름 (새이름)

    // ** information for Corporate Member *************************
    private String corpNo; // 사업자번호
    private String corpName; // 사업자명
    private String industryField; // 업종
}
