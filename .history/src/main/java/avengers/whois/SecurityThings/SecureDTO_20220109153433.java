package avengers.whois.SecurityThings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import avengers.whois.domain.member.WorkerMember;
import avengers.whois.web.member.WorkerMemberDto;

public class SecureDTO extends User {
    public SecureDTO(WorkerMember a) {
        super(a.getEmail(), a.getPassword(), a.getRoles().stream().map);
    }

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

    private List<String> roles;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
