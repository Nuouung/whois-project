package avengers.whois.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMemberDto {
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
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
    // 어차피 얘들은 회원가입하면서 api로 강제 not null 될 애들
    private String corpNo; // 사업자번호
    private String corpName; // 사업자명
    private String industryField; // 업종
}
