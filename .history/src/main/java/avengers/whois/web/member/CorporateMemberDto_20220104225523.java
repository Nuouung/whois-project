package avengers.whois.web.member;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorporateMemberDto {
    private long id;

    private String email;
    private String password;

    private String repName;// 담당자명
    private String repPhoneNumber; // 담당자번호

    private String corpNo;// 사업자번호
    private String corpName; // 사업자명
    private String industryField;// 업종
    private String corpAddress;// 사업자주소
    private LocalDateTime establishedDate;// 설립일

    // img
    private String fname;// 업로드된 파일이름 (새이름)

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
