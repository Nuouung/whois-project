package avengers.whois.web.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import avengers.whois.domain.file.FileService;
import avengers.whois.web.member.NewMemberDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/file")
public class FileController {
	
	private final FileService service;
	
	@PostMapping("/upload")
	public void fileupload(FileDto dto, NewMemberDto memdto) {
		service.fileUploadAndSave(dto, memdto);
	}
	
	
	
	
	
}
