package org.kristaps.PP12Serveris.services;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ImageService {

    private final ShopRepository shopRepository;

    @Value("${app.upload-dir}")
    private String uploadDir;

    // String uploadDir = System.getProperty("user.dir") + "/bildes";

    public String uploadImage(MultipartFile file, Long id) {
        try {
            if (file == null || file.isEmpty()) {
                System.out.println("Upload failed: no file provided");
                return null;
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isBlank()) {
                System.out.println("Upload failed: filename is null or empty");
                return null;
            }

            Path imagePath = Path.of(uploadDir);
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }

            Path filePath = imagePath.resolve(fileName);
            try (OutputStream out = Files.newOutputStream(filePath,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
                file.getInputStream().transferTo(out);
            }

            ShopModel shopModel = shopRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Auto not found with id: " + id));
            shopModel.setImageUrl(fileName);
            shopRepository.save(shopModel);
            return fileName;

        } catch (IOException e) {
            System.err.println("IO error during image upload:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during image upload: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Resource getImage(String filename) {
        try {
            Path imagePath = Path.of(uploadDir);
            Path filePath = imagePath.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                System.out.println("Image not found: " + filename);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error during image retrieval: " + e);
            return null;
        }
    }

}