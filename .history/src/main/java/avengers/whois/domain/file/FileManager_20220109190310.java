package avengers.whois.domain.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Componene
public class FileManager {

    @Value("${file.dir}")
    private String fileDir;

    /**
     * 파일의 이름을 변환하고 파일을 물리 저장소에 저장한다.
     */
    public String convertAndStore(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String convertedFilename = extractConvertedName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(convertedFilename)));
        return convertedFilename;
    }

    private String getFullPath(String convertedFilename) {
        return fileDir + convertedFilename;
    }

    private String extractConvertedName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        int pos = originalFilename.lastIndexOf(".");

        return uuid + originalFilename.substring(pos);
    }
}
