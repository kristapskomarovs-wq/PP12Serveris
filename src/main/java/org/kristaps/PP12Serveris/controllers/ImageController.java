package org.kristaps.PP12Serveris.controllers;

import java.util.Map;

import org.kristaps.PP12Serveris.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/api/v1/uploadimage")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("id") Long id) {
        String fileName = imageService.uploadImage(file, id);
        if (fileName != null) {
            return ResponseEntity.ok(Map.of("fileName", fileName));
        } else {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to upload image"));
        }
    }

    @GetMapping("/api/v1/getimage/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
        Resource image = imageService.getImage(filename);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        String contentType = org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
        String lower = filename.toLowerCase();
        if (lower.endsWith(".png"))
            contentType = org.springframework.http.MediaType.IMAGE_PNG_VALUE;
        else if (lower.endsWith(".gif"))
            contentType = org.springframework.http.MediaType.IMAGE_GIF_VALUE;
        else if (lower.endsWith(".webp"))
            contentType = "image/webp";
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, contentType)
                .body(image);
    }
}
