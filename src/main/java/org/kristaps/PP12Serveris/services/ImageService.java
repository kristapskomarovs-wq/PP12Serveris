package org.kristaps.PP12Serveris.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.kristaps.PP12Serveris.models.ShopModel;
import org.kristaps.PP12Serveris.services.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ShopService shopService;

    String uploadDir = "C:\\Users\\krist\\OneDrive\\Desktop\\RigaCoding\\PP12_Serveris\\PP12Serveris\\bildes";

    public String uploadImage(MultipartFile file, Long id) {
        try {
            Path imagePath = Path.of(uploadDir);
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = imagePath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            ShopModel shopModel = shopService.getAllAutos().stream().filter(shop -> shop.getId().equals(id)).findFirst()
                    .orElseThrow(() -> new RuntimeException("Shop not found"));
            shopModel.setImageUrl(fileName);
            shopService.updateAuto(id, shopModel);
            return fileName;

        } catch (IOException e) {
            System.out.println("Error while accessing image directory: " + e);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }
}