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
                        .stream()
                        .map(content -> new SimpleGrantedAuthority(content))
                        .collect(Collectors.toSet()));
        this.memberType = data.getMemberType();
    }

    // 다른건 몰라도 이건 항상 들고다녀야하는것같다.
    private char memberType; // Jan.10 추가 | C(기업) or W(개인)
}
