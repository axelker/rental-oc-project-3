package com.openclassrooms.rental.service.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final static String UPLOAD_DIR = "images/";

    public String saveImage(MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = buildRandomFileName(file.getOriginalFilename());
        Path filepath = Paths.get(UPLOAD_DIR, filename);

        Files.write(filepath, file.getBytes());

        return new StringBuilder("/").append(UPLOAD_DIR).append(filename).toString();
    }

    private String buildRandomFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }
}
