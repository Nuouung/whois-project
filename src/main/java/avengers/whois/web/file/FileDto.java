package avengers.whois.web.file;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import avengers.whois.domain.member.NewMember;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.NewMemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileDto {
	
	private long fno;
	private String filePath;
	private String fileOrgName;
	private String fileNewName;
	
	
}
