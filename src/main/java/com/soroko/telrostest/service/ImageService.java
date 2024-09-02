package com.soroko.telrostest.service;

import com.soroko.telrostest.entity.Image;
import com.soroko.telrostest.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * This class consists logic of image data
 *
 * @author yuriy.soroko
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageService {
    /**
     * inject image repository in order to put and get data from database
     */
    final ImageRepository imageRepository;

    /**
     * Get all images from database
     *
     * @return List of the available images
     */
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }


    public Image getImageByName(String name) {
        return imageRepository.findByName(name).orElseThrow();
    }

    /**
     * Add image to database
     *
     * @param fileImage image to add
     * @param name      name of the image to add
     */
    public Image addImage(MultipartFile fileImage, String name) throws IOException {
        Image image = new Image();
        image.setName(name);
        image.setImage(fileImage.getBytes());
        return imageRepository.save(image);
    }

    public Image updateImage(String name, MultipartFile fileImage) throws IOException {
        Image imageToBeUpdated = getImageByName(name);
        imageToBeUpdated.setImage(fileImage.getBytes());
        return imageRepository.save(imageToBeUpdated);
    }

    /**
     * Delete image by id
     *
     * @param id - id of the user to remove from the database
     */
    public void deleteImage(long id) {
        imageRepository.deleteById(id);
    }
}
