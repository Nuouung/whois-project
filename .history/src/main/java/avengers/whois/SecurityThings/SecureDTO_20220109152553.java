package avengers.whois.SecurityThings;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.User;

public class SecureDTO extends User {
    private long id;

    private String email;
    private String password;

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private char gender;

    private boolean finding; // 구직여부 T:구함
    private String address;

    // img
    private String fname; // 업로드된 파일이름 (새이름)

    // resume
    private String resume; // 이력서 파일이름 (새이름)

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
