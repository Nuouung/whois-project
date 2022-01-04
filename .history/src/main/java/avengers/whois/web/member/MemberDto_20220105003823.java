package avengers.whois.web.member;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class MemberDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private char gender;

    public MemberDto() {
    }

    public MemberDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
