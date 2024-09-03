package com.soroko.telrostest.controller;

import com.soroko.telrostest.dto.ContactDTO;
import com.soroko.telrostest.entity.Contact;
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

    /**
     * Get all contact from the service
     *
     * @return returns list of all modified contacts by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all contacts")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contactsDTO = contactService.getAllContacts();
        return ResponseEntity.ok(contactsDTO);
    }

    /**
     * Get contact by id
     *
     * @param id id of the contact
     * @return returns contact data
     */
    @GetMapping()
    @Operation(summary = "Get information about contact by id")
    public ResponseEntity<Contact> getContactById(
                                                  @Parameter(description = "contact id", example = "1")
                                                  @RequestParam int id) {
        var contact = contactService.getContactById(id);
        if (contact == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(contact);

    }

    /**
     * Add contact to the system
     *
     * @param contactDTO contact that is required to add in the system
     * @return returns contact which was added
     */
    @PostMapping()
    @Operation(summary = "Add contact to the system")
    public ResponseEntity<ContactDTO> addContact(@RequestBody ContactDTO contactDTO) {
        contactService.addContact(contactDTO);
        log.info("Adding new contact : {}", contactDTO);
        return ResponseEntity.ok(contactDTO);
    }

    /**
     * @param contactDTO contact that is required to edit in the system
     * @return returns contact which was edited
     */
    @PatchMapping()
    @Operation(summary = "Edit existing contact of the system")
    public ResponseEntity<ContactDTO> updateContact(@RequestParam long id, @RequestBody ContactDTO contactDTO) {
        contactService.updateContact(id, contactDTO);
        log.info("Updating contact : {}", contactDTO);
        return ResponseEntity.ok(contactDTO);
    }

    /**
     * @param id id of the contact that is required to delete from the system
     */
    @DeleteMapping()
    @Operation(summary = "Delete contact from the system")
    public void deleteContact(
                           @Parameter(description = "contact id", example = "1")
                           @RequestParam long id) {
        contactService.deleteContact(id);
    }
}
