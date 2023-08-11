package com.kujproject.kuj.web.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileManager {
    @Value("${upload.path.upload-images}")
    private String uploadPath;


    public Path loadFilePath(String storedFileName) throws IOException {
        return Path.of(uploadPath + File.separator + storedFileName);
    }



    public String[] saveFile(MultipartFile multipartFile, String storedFileName) throws IOException {
        if(storedFileName == "" || storedFileName == null) {
            storedFileName = UUID.randomUUID().toString() + "." + multipartFile.getContentType().split("/")[1];
        }

        String uploadFileName = multipartFile.getOriginalFilename();
        Path filePath = Path.of(uploadPath + File.separator + storedFileName);

        try {
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }

        return new String[]{uploadFileName, storedFileName};
    }
}
