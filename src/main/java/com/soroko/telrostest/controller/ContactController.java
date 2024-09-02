package com.soroko.telrostest.controller;

import com.soroko.telrostest.dto.ContactDTO;
import com.soroko.telrostest.entity.Contact;
import com.soroko.telrostest.mapper.ContactMapper;
import com.soroko.telrostest.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class consists REST API logic of contacts
 * @author yuriy.soroko
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
@Tag(
        name = "Contacts",
        description = "All methods to work with contact data of the system"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactController {
    final ContactService contactService;
    final ContactMapper contactMapper;

    /**
     * Get all contact from the service
     *
     * @return returns list of all modified contacts by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all contacts")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        List<ContactDTO> contactDTO = contactMapper.toContactDTOList(contacts);
        return ResponseEntity.ok(contactDTO);
    }

    /**
     * Get contact by id
     *
     * @param id id of the contact
     * @return returns contact data
     */
    @GetMapping("/get")
    @Operation(summary = "Get information about contact by id")
    public ResponseEntity<ContactDTO> getContactById(
                                                  @Parameter(description = "contact id", example = "1")
                                                  @RequestParam int id) {
        var contact = contactService.getContactById(id);
        if (contact == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        ContactDTO contactDTO = contactMapper.toContactDTO(contact);
        return ResponseEntity.ok(contactDTO);

    }

    /**
     * Add contact to the system
     *
     * @param contactDTO contact that is required to add in the system
     * @return returns contact which was added
     */
    @PostMapping("/add")
    @Operation(summary = "Add contact to the system")
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = contactMapper.toContact(contactDTO);
        contactService.addContact(contact);
        log.info("Adding new contact : {}", contact);
        return ResponseEntity.ok(contact);
    }

    /**
     * @param contactDTO contact that is required to edit in the system
     * @return returns contact which was edited
     */
    @PatchMapping("/edit")
    @Operation(summary = "Edit existing contact of the system")
    public ResponseEntity<Contact> updateContact(@RequestParam long id, @RequestBody ContactDTO contactDTO) {
        Contact contact = contactMapper.toContact(contactDTO);
        contactService.updateContact(id, contact);
        log.info("Updating contact : {}", contact);
        return ResponseEntity.ok(contact);
    }

    /**
     * @param id id of the contact that is required to delete from the system
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete contact from the system")
    public void deleteContact(
                           @Parameter(description = "contact id", example = "1")
                           @RequestParam long id) {
        contactService.deleteContact(id);
    }
}
