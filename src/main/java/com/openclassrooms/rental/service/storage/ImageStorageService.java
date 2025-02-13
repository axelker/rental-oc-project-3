package com.openclassrooms.rental.service.storage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import net.coobird.thumbnailator.Thumbnails;

import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ImageStorageService {

    private static final Logger log = LoggerFactory.getLogger(ImageStorageService.class);
    @Value("${server.servlet.context-path:/}")
    private String contextPath;
    @Value("${file.upload-images-dir}")
    private String uploadImgDir;

    private final List<String> allowedExtensions = List.of("jpg", "jpeg", "png", "gif");

    public String saveImage(MultipartFile file, String fileName) throws IOException {
        validateFile(file);

        Path parentDir = getUploadDirPath();
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
            log.info("Create folder : {}", parentDir);
        }
        Path filePath = parentDir.resolve(fileName);
        byte[] compressedImage = compressImage(file);
        Files.write(filePath, compressedImage);

        log.info("File save in : {}", filePath);
        return filePath.toString();
    }

    public byte[] loadImage(String fileName) throws NoSuchElementException, IOException {
        Path uploadPath = getUploadDirPath();
        Path filePath = uploadPath.resolve(fileName).normalize();
        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            throw new NoSuchElementException("File not found: " + fileName);
        }
        return Files.readAllBytes(filePath);
    }

    private Path getUploadDirPath() {
        return Paths.get("").toAbsolutePath().resolve(uploadImgDir).normalize();
    }

    private void validateFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            log.error("Unsupported file format: {}", contentType);
            throw new IllegalArgumentException("File format is not supported.");
        }

        validateFileExtension(file.getOriginalFilename());
    }

    private void validateFileExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        if (!allowedExtensions.contains(extension)) {
            log.error("File extension not allowed: {}", extension);
            throw new IllegalArgumentException("File extension is not allowed.");
        }
    }

    private byte[] compressImage(MultipartFile file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Thumbnails.of(new ByteArrayInputStream(file.getBytes()))
                .size(800, 800)
                .outputQuality(0.7)
                .toOutputStream(baos);
        return baos.toByteArray();
    }

    public String buildCompleteUrlFile(String fileName) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        return new StringBuilder().append(baseUrl)
                .append("/storage/images").append("/" + fileName).toString();
    }

    public String buildRandomFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }
}
