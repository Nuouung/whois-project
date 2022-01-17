package avengers.whois.domain.file;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import avengers.whois.domain.member.NewMember;
import avengers.whois.domain.member.NewMemberRepository;
import avengers.whois.web.file.FileDto;
import avengers.whois.web.member.NewMemberDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService{
	
	
	private final MultiFileRepository repository;
	private final NewMemberRepository memrepository;
	
	public String fileUploadAndSave(FileDto dto, MultipartHttpServletRequest req) {
		
		Iterator<String> it=req.getFileNames();
		while(it.hasNext()) {
			List<MultipartFile> list=req.getFiles(it.next());
		}
		return "multifile/list";
	}
	
	public String fileUploadAndSave(FileDto dto, NewMemberDto memdto) {
		
		List<MultiFile> files=fileUpload(memdto.getFile());
		
		NewMember entity=NewMember.builder()
				.email(memdto.getEmail())
				.file(files)
				.build();
		memrepository.save(entity);
		return "/member/worker/myinfo";
	}
	
	private List<MultiFile> fileUpload(List<MultipartFile> files) {
		List<MultiFile> result=new Vector<>(); 
		
		for(MultipartFile mf : files) {
			
			String fileOrgName= mf.getOriginalFilename();
			String[] strs=fileOrgName.split("[.]");
			System.out.println(strs.length);
			System.out.println("파일이름 : "+ strs[0]);
			System.out.println("확장자 : "+ strs[1]);
			String fileNewName=strs[0]+"_"+(System.nanoTime()/100000)+"."+strs[1];
			String filePath="/img/file/";
			MultiFile entity=MultiFile.builder()
					.fileOrgName(fileOrgName).fileNewName(fileNewName).filePath(filePath)
					.build();
			result.add(entity);
			
			ClassPathResource cpr=new ClassPathResource("static"+filePath);
			try {
				File location=cpr.getFile();
				mf.transferTo(new File(location, fileNewName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
		
		public void fileDownload(long fno, HttpServletResponse response, FileDto dto) {

			MultiFile result=repository.findById(fno).orElseThrow();
			
			ClassPathResource cpr=new ClassPathResource("static"+result.getFilePath());
			try {
				File downloadFile=new File(cpr.getFile(), result.getFileNewName());
				byte[] downfile=FileUtils.readFileToByteArray(downloadFile);
				response.setContentType("aplication/octet-stream");
				response.setHeader("Content-Disposition", "attachment; fileName=\""+
						URLEncoder.encode(result.getFileOrgName(),"UTF-8")+"\";");
				
				response.setHeader("Content-Transfer-Encoding","binary");
				
				response.getOutputStream().write(downfile);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
}
