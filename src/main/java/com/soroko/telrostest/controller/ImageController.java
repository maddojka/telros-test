package com.soroko.telrostest.controller;

import com.soroko.telrostest.entity.Image;
import com.soroko.telrostest.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * This class consists REST API logic of images
 *
 * @author yuriy.soroko
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    final ImageService imageService;

    /**
     * Get all images from the service
     *
     * @return returns list of all modified images
     */
    @GetMapping()
    @Operation(summary = "Get information about all images")
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return ResponseEntity.ok(images);
    }

    /**
     * Get image by id
     *
     * @param imageName name of the image
     * @return returns image data
     */
    @GetMapping("/{imageName}")
    @Operation(summary = "Get information about image by id")
    public ResponseEntity<byte[]> getImageById(
            @Parameter(description = "name of the image", example = "image01")
            @PathVariable String imageName) {
        byte[] imageData = imageService.getImageByName(imageName).getImage();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }

    /**
     * Add image to the system
     *
     * @param fileImage file image that is required to add in the system
     * @return returns image which was added
     */
    @PostMapping()
    @Operation(summary = "Add image to the system")
    public ResponseEntity<MultipartFile> addImage(
            @RequestParam("image") MultipartFile fileImage,
            @Parameter(description = "name of the image", example = "photo")
            @RequestParam("name") String name) throws IOException {
        imageService.addImage(fileImage, name);
        log.info("Adding new image : {}", fileImage);
        return ResponseEntity.ok(fileImage);
    }

    /**
     * @param fileImage image that is required to edit in the system
     * @param name      name of the image that is required to edit in the system
     * @return returns image which was edited
     */
    @PatchMapping()
    @Operation(summary = "Edit existing image of the system")
    public ResponseEntity<MultipartFile> updateImage(
            @Parameter(description = "name of the image", example = "photo")
            @RequestParam String name,
            @RequestParam("image") MultipartFile fileImage)
            throws IOException {
        imageService.updateImage(name, fileImage);
        log.info("Updating image : {}", fileImage);
        return ResponseEntity.ok(fileImage);
    }

    /**
     * @param id id of the image that is required to delete from the system
     */
    @DeleteMapping()
    @Operation(summary = "Delete image from the system")
    public void deleteImage(
            @Parameter(description = "image id", example = "1")
            @RequestParam long id) {
        imageService.deleteImage(id);
    }
}
