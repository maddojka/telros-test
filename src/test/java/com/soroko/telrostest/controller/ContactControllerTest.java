package com.soroko.telrostest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soroko.telrostest.entity.Contact;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    private ContactController controller;

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
    @DisplayName("Check for getting correct list of contact data from request")
    void test_canGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/contact/all")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for getting contact data by id")
    void test_canGetUserById() throws Exception {
        mockMvc.perform(get("/api/v1/contact/get?id=1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for adding correct contact data")
    void test_canAddUser() throws Exception {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setEmail("user@email.com");
        contact.setPhone("123456789");
        String userJson = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post("/api/v1/contact/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for editing existing user")
    void test_canUpdateUser() throws Exception {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setEmail("user@email.com");
        contact.setPhone("123456789");
        String userJson = objectMapper.writeValueAsString(contact);
        mockMvc.perform(patch("/api/v1/contact/edit?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Check for deleting existing user")
    void test_canDeleteUser() throws Exception {
        String userJson = objectMapper.writeValueAsString(1);
        mockMvc.perform(delete("/api/v1/contact/delete?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }
}