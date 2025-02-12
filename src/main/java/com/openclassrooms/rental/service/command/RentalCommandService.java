package com.openclassrooms.rental.service.command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.rental.model.RentalEntity;
import com.openclassrooms.rental.repository.RentalRepository;
import com.openclassrooms.rental.service.storage.ImageStorageService;

@Service
public class RentalCommandService {
        private final RentalRepository rentalRepository;
        private final ImageStorageService imageStorageService;

        public RentalCommandService(RentalRepository rentalRepository,
                        ImageStorageService imageStorageService) {
                this.rentalRepository = rentalRepository;
                this.imageStorageService = imageStorageService;
        }

        @Transactional(rollbackFor = IOException.class)
        public void createRental(String name,
                        double surface,
                        double price,
                        String description,
                        MultipartFile picture,
                        Integer userId) throws IOException {
                String randomFileName = imageStorageService.buildRandomFileName(picture.getOriginalFilename());
                String pictureUrl = imageStorageService.buildCompleteUrlFile(randomFileName);
                rentalRepository.save(RentalEntity.builder()
                                .name(name)
                                .surface(surface)
                                .price(price)
                                .description(description)
                                .picture(pictureUrl)
                                .owner_id(userId)
                                .build());
                imageStorageService.saveImage(picture, randomFileName);
        }

        public void updateRental(Integer id,
                        String name,
                        double surface,
                        double price,
                        String description) throws NoSuchElementException {

                RentalEntity rentalToUpdate = rentalRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Rental not found for update"));
                rentalRepository.save(rentalToUpdate.toBuilder()
                                .name(name)
                                .surface(surface)
                                .price(price)
                                .description(description)
                                .build());
        }

}
