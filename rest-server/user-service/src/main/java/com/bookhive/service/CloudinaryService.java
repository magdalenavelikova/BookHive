package com.bookhive.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
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

    public String uploadAvatar(MultipartFile multipartFile) throws IOException {
        String publicId = uploadImage(multipartFile);

        Transformation transformation = new Transformation()
                .width(100)
                .height(100)
                .radius("max")
                .gravity("face")
                .crop("fill");

        return cloudinary.url().secure(true)
                .transformation(transformation)
                .format("png")
                .generate(publicId);
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return this.cloudinary
                .uploader()
                .upload(file, Collections.emptyMap())
                .get("public_id")
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


//    public String uploadImage(MultipartFile multipartFile) throws IOException {
//        File file = File.createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
//        multipartFile.transferTo(file);
//
//        return this.cloudinary
//                .uploader()
//                .upload(file, Collections.emptyMap())
//                .get(URL)
//                .toString();
//    }


}
