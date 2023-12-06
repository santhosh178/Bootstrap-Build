package com.example.firstproject.service;

import com.example.firstproject.entity.Image;
import com.example.firstproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(String imageName, byte[] imageData) {
        Image image = new Image();
        image.setImageName(imageName);
        image.setImageData(imageData);

        imageRepository.save(image);
    }

    public Optional<Image> getImageById(long id) {
        return imageRepository.findById(id);
    }
}
