package com.openclassrooms.rental.service.command;

import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;
import com.openclassrooms.rental.service.storage.ImageStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalCommandServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private ImageStorageService imageStorageService;

    @InjectMocks
    private RentalCommandService rentalCommandService;

    @Mock
    private MultipartFile multipartFile;

    @Test
    void testCreateRental_successful() throws IOException {
        String pictureUrl = "uploads/test-image.jpg";
        when(imageStorageService.buildCompleteUrlFile(multipartFile.getOriginalFilename())).thenReturn(pictureUrl);

        RentalEntity savedEntity = RentalEntity.builder()
                .name("Test Rental")
                .surface(100.0)
                .price(1500.0)
                .description("Test Description")
                .picture(pictureUrl)
                .build();
        when(rentalRepository.save(any(RentalEntity.class))).thenReturn(savedEntity);

        rentalCommandService.createRental("Test Rental", 100.0, 1500.0, "Test Description", multipartFile);

        verify(imageStorageService).buildCompleteUrlFile(multipartFile.getOriginalFilename());
        ArgumentCaptor<RentalEntity> captor = ArgumentCaptor.forClass(RentalEntity.class);
        verify(rentalRepository).save(captor.capture());
        RentalEntity entityCaptured = captor.getValue();

        assertEquals("Test Rental", entityCaptured.getName());
        assertEquals(100.0, entityCaptured.getSurface());
        assertEquals(1500.0, entityCaptured.getPrice());
        assertEquals("Test Description", entityCaptured.getDescription());
        assertEquals(pictureUrl, entityCaptured.getPicture());
    }

    @Test
    void testUpdateRental_successful() {
        Integer rentalId = 1;
        RentalEntity existingRental = RentalEntity.builder()
                .id(rentalId)
                .name("Old Name")
                .surface(80.0)
                .price(1200.0)
                .description("Old Description")
                .picture("uploads/oldImage.jpg")
                .build();
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(existingRental));

        RentalEntity updatedRental = RentalEntity.builder()
                .id(rentalId)
                .name("New Name")
                .surface(90.0)
                .price(1300.0)
                .description("New Description")
                .picture("uploads/oldImage.jpg")
                .build();
        when(rentalRepository.save(any(RentalEntity.class))).thenReturn(updatedRental);

        rentalCommandService.updateRental(rentalId, "New Name", 90.0, 1300.0, "New Description");

        ArgumentCaptor<RentalEntity> captor = ArgumentCaptor.forClass(RentalEntity.class);
        verify(rentalRepository).save(captor.capture());
        RentalEntity savedEntity = captor.getValue();

        assertEquals(rentalId, savedEntity.getId());
        assertEquals("New Name", savedEntity.getName());
        assertEquals(90.0, savedEntity.getSurface());
        assertEquals(1300.0, savedEntity.getPrice());
        assertEquals("New Description", savedEntity.getDescription());
        assertEquals("uploads/oldImage.jpg", savedEntity.getPicture());
    }

    @Test
    void testUpdateRental_notFound() {
        Integer rentalId = 1;
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> rentalCommandService.updateRental(rentalId, "New Name", 90.0, 1300.0, "New Description"));
        assertEquals("Rental not found for update", ex.getMessage());
    }
}
