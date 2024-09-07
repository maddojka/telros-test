package com.soroko.telrostest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * This class consists fields to store user data to database
 *
 * @author yuriy.soroko
 */
@Entity
@Table(name = "app_user")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    /**
     * Basic fields of the users
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotNull(message = "First name of the user is required")
    @NotBlank(message = "First name of the user is required")
    @Column(name = "first_name")
    String firstName;
    @NotNull(message = "Last name of the user is required")
    @NotBlank(message = "Last name of the user is required")
    @Column(name = "last_name")
    String lastName;
    @NotNull(message = "Middle name of the user is required")
    @NotBlank(message = "Middle name of the user is required")
    @Column(name = "middle_name")
    String middleName;
    @NotNull(message = "Birth date is required")
    @DateTimeFormat
    @Column(name = "birth_date", nullable = false)
    LocalDate birthDate;
    @OneToOne
    Contact contact;
    @OneToOne
    Image image;
}
