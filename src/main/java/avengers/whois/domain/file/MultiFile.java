package avengers.whois.domain.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MultiFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fno;
	@Column(nullable=false)
	private String filePath;
	@Column(nullable=false)
	private String fileOrgName;
	@Column(nullable=false)
	private String fileNewName;

}
