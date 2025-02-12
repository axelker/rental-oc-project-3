package com.openclassrooms.rental.service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ImageStorageServiceTest {
    @TempDir
    Path tmpDir;

    private ImageStorageService imageStorageService;

    @BeforeEach
    void setUp() {
        imageStorageService = new ImageStorageService();
        ReflectionTestUtils.setField(imageStorageService, "uploadImgDir", tmpDir.toString());

    }

    @Test
    void testSaveImage_Success() throws IOException {
        Path targetFilePath = tmpDir.resolve("test-image.jpg");

        byte[] imageBytes = Files.readAllBytes(Path.of("src/test/resources/images/test-image.jpg"));

        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn(imageBytes);
        when(mockFile.getOriginalFilename()).thenReturn("test-image.jpg");
        when(mockFile.getContentType()).thenReturn("image/jpeg");

        String storedFilePath = imageStorageService.saveImage(mockFile, targetFilePath.toString());

        assertThat(storedFilePath).isEqualTo(targetFilePath.toString());
        assertThat(Files.exists(targetFilePath)).isTrue();
    }

    @Test
    void buildRandomlFileName_assertDifferent() {
        String fileName = "test-file-name.png";

        String fileRandomName1 = imageStorageService.buildRandomFileName(fileName);
        String fileRandomName2 = imageStorageService.buildRandomFileName(fileName);

        assertThat(fileRandomName1).isNotEqualTo(fileRandomName2);
        assertThat(fileRandomName1).contains(fileName);
        assertThat(fileRandomName2).contains(fileName);
    }

    @Test
    void buildCompletUrlFileName_assertDifferent() {
        String fileName = "test-file-name.png";

        String url = imageStorageService.buildCompleteUrlFile(fileName);

        assertThat(url).contains("/storage/images");
        assertThat(url).contains(fileName);
    }

    @Test
    void testSaveImage_InvalidMimeType_ThrowsException() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test-image.jpg");
        when(mockFile.getContentType()).thenReturn("application/pdf");

        assertThatThrownBy(() -> imageStorageService.saveImage(mockFile, tmpDir.resolve("test-image.jpg").toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File format is not supported.");
    }
}