package avengers.whois.domain.file;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static String tempPath;
	private static String fileName;
	
	public static String tempImgUpload(MultipartFile fileImg, String path) {
		
		tempPath=path;
		
		fileName=fileImg.getOriginalFilename();
		
		ClassPathResource cpr=new ClassPathResource("static"+path);
		try {
			File tempLocation = cpr.getFile();
			for(File file : tempLocation.listFiles())file.delete();
			
			fileImg.transferTo(new File(tempLocation, fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path+fileName;
	}
	public static void moveTempToDest(String destPath) {
		
		ClassPathResource tempCpr=new ClassPathResource("static"+tempPath);
		ClassPathResource destCpr=new ClassPathResource("static"+destPath);
		
		try {
			File tempFile = new File(tempCpr.getFile(), fileName);
			tempFile.renameTo(new File(destCpr.getFile(), fileName));
			tempFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
