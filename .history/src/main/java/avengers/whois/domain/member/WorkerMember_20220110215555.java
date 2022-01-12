package avengers.whois.domain.member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "worker")
public class WorkerMember {

    @Id
    @Column(name = "WORKER_ID")
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private boolean finding; // 구직여부 T:구함
    @Column(nullable = false)
    private String address;

    // img
    @Column(nullable = true)
    private String fname; // 업로드된 파일이름 (새이름)

    // resume
    @Column(nullable = true)
    private String resume; // 이력서 파일이름 (새이름)

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void setFName(String fname) {
        this.fname = fname;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}