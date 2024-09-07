package com.soroko.telrostest.service;


import com.soroko.telrostest.dto.UserDTO;
import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.entity.Image;
import com.soroko.telrostest.entity.User;
import com.soroko.telrostest.repository.ContactRepository;
import com.soroko.telrostest.repository.ImageRepository;
import com.soroko.telrostest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
@SpringBootTest
public class UserServerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ImageRepository imageRepository;
    UserDTO userDTO;
    Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact();
        contact.setId(1);
        contact.setPhone("9211234568");
        contact.setEmail("contact@email.com");
        contactRepository.save(contact);
        Image image = new Image();
        image.setId(1);
        image.setName("image01");
        imageRepository.save(image);
        userDTO = new UserDTO
                (1, "Ivan", "Ivanov", "Ivanovich",
                        LocalDate.of(1990, 1, 1), contact);
        User user = new User();
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
    @DisplayName("Check add user method - user is OK")
    public void addUser_isNotNull() {
        userService.addUser(userDTO);
    }

    @Test
    @DisplayName("Check get user method - user id is OK")
    public void getUser_correctId() {
        userService.getAllUsers().add(userDTO);
        userService.getUserById(1);
    }

    @Test
    @DisplayName("Check edit user method - user id is OK")
    public void editUser_correctId() {
        userService.addUser(userDTO);
        userService.updateUser(1, userDTO);
    }

    @Test
    @DisplayName("Check remove user method - user id is OK")
    public void removeUser_correctId() {
        userService.getAllUsers().add(userDTO);
        userService.deleteUser(1);
    }
}
