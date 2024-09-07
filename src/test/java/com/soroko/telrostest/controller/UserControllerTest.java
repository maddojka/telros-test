package com.soroko.telrostest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.entity.Image;
import com.soroko.telrostest.entity.User;
import com.soroko.telrostest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserController controller;

    @InjectMocks
    private UserService userService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Check for getting correct list of users from request")
    void test_canGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/user/all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting user by id")
    void test_canGetUserById() throws Exception {
        mockMvc.perform(get("/api/v1/user/get?id=1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for adding correct user")
    void test_canAddUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setMiddleName("Ivanovich");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
//        user.setContact(new Contact());
        user.setImage(new Image());
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/api/v1/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for editing existing user")
    void test_canUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setMiddleName("Ivanovich");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
//        user.setContact(new Contact());
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(patch("/api/v1/user/edit?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for deleting existing user")
    void test_canDeleteUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(1);
        mockMvc.perform(delete("/api/v1/user/delete?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }
}