package com.soroko.telrostest.repository;


import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.entity.Image;
import com.soroko.telrostest.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine");
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ImageRepository imageRepository;
    private User user;
    private Contact contact;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        contactRepository.deleteAll();
        contact = new Contact();
        contact.setId(1);
        contact.setPhone("9211234568");
        contact.setEmail("contact@email.com");
        contactRepository.save(contact);
        Image image = new Image();
        image.setId(1);
        image.setName("image01");
        imageRepository.save(image);
        user = new User();
        user.setId(1);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setMiddleName("Ivanovich");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
//        user.setContact(contact);
        user.setImage(image);
        userRepository.save(user);
    }


    @Test
    @DisplayName("Checking get all users method - shouldn't be empty")
    void test_shouldFindAll() {
        assertEquals(userRepository.findAll().size(), 1);
    }

    @Test
    @DisplayName("Checking user insertion method - should insert user")
    void test_shouldSaveUser() {
        User retreivedUser = userRepository.getReferenceById(user.getId());
        Assertions.assertEquals(user.getBirthDate(), retreivedUser.getBirthDate());
    }

    @Test
    @DisplayName("Checking user updating - should update user")
    void test_shouldUpdateUser() {
        User retrievedUser = userRepository.getReferenceById(1L);
        retrievedUser.setLastName("Popov");
        userRepository.save(retrievedUser);
        User updatedUser = userRepository.getReferenceById(1L);
        Assertions.assertEquals(updatedUser.getLastName(), retrievedUser.getLastName());
    }

    @Test
    @DisplayName("Checking user deleting - should delete user")
    void test_shouldDeleteUserById() {
        userRepository.deleteById(user.getId());
        Assertions.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking user finding - should find user by user id")
    void test_shouldFindUserById() {
        User retrievedUser = userRepository.getReferenceById(1L);
        Assertions.assertNotNull(retrievedUser);
    }
}