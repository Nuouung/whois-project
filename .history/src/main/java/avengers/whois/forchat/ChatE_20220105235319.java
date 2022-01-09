package avengers.whois.forchat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "CHATROOMS")
@EntityListeners(AuditingEntityListener.class)
public class ChatE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private long chatNo;
}
