package com.soroko.telrostest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * This class consists contact data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 */
public record ContactDTO(
        @Schema(description = "id", example = "1")
        long id,
        @Schema(description = "email", example = "user@example.com")
        String email,
        @Schema(description = "phone", example = "9211230457")
        String phone
) {
}
