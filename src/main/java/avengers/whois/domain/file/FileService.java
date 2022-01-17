package avengers.whois.domain.file;

import avengers.whois.web.file.FileDto;
import avengers.whois.web.member.NewMemberDto;

public interface FileService {

	String fileUploadAndSave(FileDto dto, NewMemberDto memdto);

}
