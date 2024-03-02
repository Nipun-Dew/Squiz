package com.example.squiz.dtos;

import com.example.squiz.entities.ImagesEB;
import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {
    private String imageName;
    private MultipartFile imageFile;

    public ImageRequest() {
    }

    public ImagesEB createImageEB(String imageUrl) {
        ImagesEB newImage = new ImagesEB();
        newImage.setImageURL(imageUrl);
        newImage.setImageName(imageName);

        return newImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
