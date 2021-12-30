package avengers.whois.web.member;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String email;
    private String password;

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private char gender;
}
