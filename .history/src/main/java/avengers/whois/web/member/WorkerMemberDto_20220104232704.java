package avengers.whois.web.member;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerMemberDto {

    private long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private char gender;

    private boolean finding;// 구직여부 T:구함
    private String address;

    // img
    private String fname; // 업로드된 파일이름 (새이름)
    // resume
    private String resume;// 이력서 파일이름 (새이름)

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}