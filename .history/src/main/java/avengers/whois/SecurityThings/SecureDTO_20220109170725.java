package avengers.whois.SecurityThings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import avengers.whois.domain.member.WorkerMember;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecureDTO extends User {
    public SecureDTO(WorkerMember a) {
        super(a.getEmail(), a.getPassword(),
                a.getRoles().stream().map(b -> new SimpleGrantedAuthority(b)).collect(Collectors.toList()));
        System.out.println(this.getUsername());
        this.id = a.getId();
        this.name = a.getName();
        this.phoneNumber = a.getPhoneNumber();
        this.birthday = a.getBirthday();
        this.gender = a.getGender();
        this.finding = a.isFinding();
        this.address = a.getAddress();
        this.fname = a.getFname();
        this.resume = a.getResume();
        this.createdDate = a.getCreatedDate();
        this.modifiedDate = a.getModifiedDate();
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
