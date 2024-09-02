package com.soroko.telrostest.dto;

import com.soroko.telrostest.entity.Contact;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
/**
 * This class consists user data transfer object fields
 * it needs to transform input data to entity
 * @author yuriy.soroko
 * @version 1.0
 */
public record UserDTO(
        @Schema(description = "id", example = "1")
        long id,
        @Schema(description = "first_name", example = "Ivan")
        String firstName,
        @Schema(description = "last_name", example = "Ivanov")
        String lastName,
        @Schema(description = "middle_name", example = "Ivanovich")
        String middleName,
        @Schema(description = "birth_date", example = "1990-1-1")
        LocalDate birthDate,
        Contact contact
) {
}
