package com.soroko.telrostest.repository;


import com.soroko.telrostest.entity.Contact;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContactRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine");
    @Autowired
    private ContactRepository contactRepository;
    private Contact contact;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        contact = new Contact();
        contact.setId(1);
        contact.setPhone("9211234568");
        contact.setEmail("contact@email.com");
        contactRepository.save(contact);
    }


    @Test
    @DisplayName("Checking get all contacts method - shouldn't be empty")
    void test_shouldFindAll() {
        assertEquals(contactRepository.findAll().size(), 1);
    }

    @Test
    @DisplayName("Checking contact insertion method - should insert contact")
    void test_shouldSaveContact() {
        Contact retreivedContact = contactRepository.getReferenceById(contact.getId());
        Assertions.assertEquals(contact.getEmail(), retreivedContact.getEmail());
    }

    @Test
    @DisplayName("Checking contact updating - should update contact")
    void test_shouldUpdateContact() {
        Contact retrievedContact = contactRepository.getReferenceById(1L);
        retrievedContact.setEmail("admin@email.com");
        contactRepository.save(retrievedContact);
        Contact updatedContact = contactRepository.getReferenceById(1L);
        Assertions.assertEquals(updatedContact.getEmail(), retrievedContact.getEmail());
    }

    @Test
    @DisplayName("Checking contact deleting - should delete contact")
    void test_shouldDeleteContactById() {
        contactRepository.deleteById(contact.getId());
        Assertions.assertTrue(contactRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Checking contact finding - should find contact by contact id")
    void test_shouldFindContactById() {
        Contact retrievedContact = contactRepository.getReferenceById(1L);
        Assertions.assertNotNull(retrievedContact);
    }
}