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
        this.corpAddress = a.getCorpAddress();
        this.establishedDate = a.getEstablishedDate();

        this.fname = a.getFname();

        this.createdDate = a.getCreatedDate();
        this.modifiedDate = a.getModifiedDate();
    }

    // variables for Worker Member from here

    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private char gender;

    private boolean finding; // 구직여부 T:구함
    private String address;

    // variables for Corporate Member from here
    private String repName; // 담당자명
    private String repPhoneNumber; // 담당자번호

    private String corpNo; // 사업자번호
    private String corpName; // 사업자명
    private String industryField; // 업종
    private String corpAddress; // 사업자주소
    private LocalDate establishedDate;// 설립일

    // common variables for both
    private long id;

    // img
    private String fname; // 업로드된 파일이름 (새이름)

    // resume
    private String resume; // 이력서 파일이름 (새이름)

    private List<String> roles;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
