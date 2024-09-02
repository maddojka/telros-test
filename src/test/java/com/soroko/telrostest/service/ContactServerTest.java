package com.soroko.telrostest.service;


import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yuriy.soroko
 */
@SpringBootTest
public class ContactServerTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;
    Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact();
        contact.setId(1);
        contact.setPhone("9211234568");
        contact.setEmail("contact@email.com");
        contactRepository.save(contact);
    }

    @Test
    @DisplayName("Check add contact method - contact is OK")
    public void addContact_isNotNull() {
        contactService.addContact(contact);
    }

    @Test
    @DisplayName("Check get contact method - contact id is OK")
    public void getContact_correctId() {
        contactService.getAllContacts().add(contact);
        contactService.getContactById(1);
    }

    @Test
    @DisplayName("Check edit contact method - contact id is OK")
    public void editContact_correctId() {
        contactService.addContact(contact);
        contactService.updateContact(1, contact);
    }

    @Test
    @DisplayName("Check remove contact method - contact id is OK")
    public void removeContact_correctId() {
        contactService.getAllContacts().add(contact);
        contactService.deleteContact(1);
    }
}
