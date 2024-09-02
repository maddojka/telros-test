package com.soroko.telrostest.service;

import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.entity.User;
import com.soroko.telrostest.repository.ContactRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class consists logic of contact data
 *
 * @author yuriy.soroko
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactService {
    /**
     * inject contact repository in order to put and get data from database
     */
    final ContactRepository contactRepository;

    /**
     * Get all contacts from database
     *
     * @return List of the available contacts
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Get contact by id
     *
     * @param id - id of the contact to get
     * @return contact as requested Contact
     */
    public Contact getContactById(long id) {
        return contactRepository.findById(id).orElseThrow();
    }

    /**
     * Add contact to database
     *
     * @param contact contact to add
     */
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Update contact
     *
     * @param updatedContact form of the contact to edit
     * @param id             id of the contact to edit
     */
    public Contact updateContact(long id, Contact updatedContact) {
        Contact contactToBeUpdated = getContactById(id);
        contactToBeUpdated.setPhone(updatedContact.getPhone());
        contactToBeUpdated.setEmail(updatedContact.getEmail());
        return contactRepository.save(contactToBeUpdated);
    }

    /**
     * Delete contact by id
     *
     * @param id id of the contact to remove from the database
     */
    public void deleteContact(long id) {
        contactRepository.deleteById(id);
    }
}
