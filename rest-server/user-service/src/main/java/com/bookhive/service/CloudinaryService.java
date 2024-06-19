package com.bookhive.service;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
@AllArgsConstructor
public class CloudinaryService {
    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";

    private final Cloudinary cloudinary;



    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return this.cloudinary
                .uploader()
                .upload(file, Collections.emptyMap())
                .get(URL)
                .toString();
    }



    public void deleteImage(String url) {

        String fileNameWithType = url.substring(url.lastIndexOf("/") + 1);
        String fileNameWithoutType = fileNameWithType.substring(0, fileNameWithType.lastIndexOf("."));
        try {
            this.cloudinary.uploader().destroy(fileNameWithoutType, Collections.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
