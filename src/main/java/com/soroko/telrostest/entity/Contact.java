package com.soroko.telrostest.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * This class consists fields to store contact data to database
 *
 * @author yuriy.soroko
 */
@Entity
@Table(name = "contact")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {
    /**
     * Basic fields of the contact
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id", example = "1")
    long id;
    @NotNull(message = "Email of the user is required")
    @NotBlank(message = "Email of the user is required")
    @Email
    @Schema(description = "email", example = "user@example.com")
    String email;
    @NotNull(message = "Phone of the user is required")
    @NotBlank(message = "Phone of the user is required")
    @Pattern(regexp = "(^$|[0-9]{10})")
    String phone;
}
