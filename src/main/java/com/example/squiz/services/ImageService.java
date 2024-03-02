package com.example.squiz.services;

import com.cloudinary.Cloudinary;
import com.example.squiz.dtos.ImageRequest;
import com.example.squiz.entities.ImagesEB;
import com.example.squiz.repos.ImagesRepository;
import org.springframework.http.ResponseEntity;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {
    @Resource
    private Cloudinary cloudinary;

    private final ImagesRepository imagesRepository;

    @Autowired
    public ImageService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    private String uploadImage(MultipartFile file) throws IOException {
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "squiz_images_1");

        Map<?, ?> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        String publicId = (String) uploadedFile.get("public_id");

        return cloudinary.url().secure(true).generate(publicId);
    }

    public ResponseEntity<Map<String, String>> getImageMetadataById(String id) {
        try {
            ImagesEB image = imagesRepository.findById(Long.parseLong(id)).orElseThrow();

            return ResponseEntity.ok().body(Map.of("name", image.getImageName(),
                    "url", image.getImageURL()));
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Map<String, String>> uploadImageAndSaveURL(ImageRequest imageRequest) {
        try {
            if (imageRequest.getImageName().isEmpty() || imageRequest.getImageFile().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            String imageURL = uploadImage(imageRequest.getImageFile());
            if(imageURL == null) {
                return ResponseEntity.badRequest().build();
            }
            ImagesEB result = imagesRepository.save(imageRequest.createImageEB(imageURL));

            return ResponseEntity.ok().body(Map.of("id", result.getId().toString(),
                    "url", imageURL));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
