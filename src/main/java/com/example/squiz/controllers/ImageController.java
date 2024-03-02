package com.example.squiz.controllers;

import com.example.squiz.dtos.ImageRequest;
import com.example.squiz.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "${api.prefix}")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Map<String, String>> getImageMetadataById(@PathVariable String id) {
        return imageService.getImageMetadataById(id);
    }

    @PostMapping("/image")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Map<String, String>> uploadImage(ImageRequest imageRequest) {
        return imageService.uploadImageAndSaveURL(imageRequest);
    }
}
