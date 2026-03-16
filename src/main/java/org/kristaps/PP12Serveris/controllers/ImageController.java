package org.kristaps.PP12Serveris.controllers;

import org.kristaps.PP12Serveris.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {
        org.springframework.web.bind.annotation.RequestMethod.POST,
        org.springframework.web.bind.annotation.RequestMethod.OPTIONS })
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/api/v1/uploadimage/")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        String fileName = imageService.uploadImage(file, id);
        if (fileName != null) {
            return ResponseEntity.ok(fileName);
        } else {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
}
