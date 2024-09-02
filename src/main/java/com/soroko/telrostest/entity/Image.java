package com.soroko.telrostest.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * This class consists fields to store image data to database
 *
 * @author yuriy.soroko
 */
@Entity
@Table(name = "image")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
    /**
     * Basic fields of the images
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id", example = "1")
    private long id;
    @NotNull
    @Schema(description = "name of the image", example = "image01")
    private String name;
    @Lob
    @Schema(description = "image to upload/download")
    byte[] image;
}
