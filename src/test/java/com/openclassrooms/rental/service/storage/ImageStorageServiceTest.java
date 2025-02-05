package com.openclassrooms.rental.service.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ImageStorageServiceTest {

    private ImageStorageService imageStorageService;

    @BeforeEach
    void setUp() {
        imageStorageService = new ImageStorageService();
    }

    @Test
    void testSaveImage_Success() throws IOException {
    
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test-image.jpg");
        when(mockFile.getBytes()).thenReturn(new byte[]{1, 2, 3, 4, 5});


        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {

            String storedFilePath = imageStorageService.saveImage(mockFile);


            assertThat(storedFilePath).startsWith("/images/");
            verify(mockFile, times(1)).getOriginalFilename();
            verify(mockFile, times(1)).getBytes();
        }
    }
}