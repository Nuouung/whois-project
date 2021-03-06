package avengers.whois.SecurityThings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import avengers.whois.domain.member.CorporateMember;
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

        System.out.println("this.getUsername() : " + this.getUsername());
        System.out.println("this.getPassword() : " + this.getPassword());
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

    public SecureDTO(CorporateMember a) {
        super(a.getEmail(), a.getPassword(),
                a.getRoles().stream().map(b -> new SimpleGrantedAuthority(b)).collect(Collectors.toList()));

        System.out.println("this.getUsername() : " + this.getUsername());
        System.out.println("this.getPassword() : " + this.getPassword());
        this.id = a.getId();
        this.repName = a.getRepName();
        this.repPhoneNumber = a.getRepPhoneNumber();
        this.corpNo = a.getCorpNo();
        this.corpName = a.getCorpName();
        this.industryField = a.getIndustryField();
        this.establishedDate = a.getEstablishedDate();
        this.fname = a.getFname();
        this.resume = a.getResume();
        this.createdDate = a.getCreatedDate();
        this.modifiedDate = a.getModifiedDate();
    }

    // variables for Worker Member from here
    private long id;

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private char gender;

    private boolean finding; // ???????????? T:??????
    private String address;

    // variables for Corporate Member from here
    private String repName; // ????????????
    private String repPhoneNumber; // ???????????????

    private String corpNo; // ???????????????
    private String corpName; // ????????????
    private String industryField; // ??????
    private String corpAddress; // ???????????????
    private LocalDate establishedDate;// ?????????

    // img
    private String fname; // ???????????? ???????????? (?????????)

    // resume
    private String resume; // ????????? ???????????? (?????????)

    private List<String> roles;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
