package com.soroko.telrostest.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class ContactTest {

    @Test
    @DisplayName("Check contact is OK")
    public void Contact_isOk() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setPhone("9211234568");
        contact.setEmail("contact@email.com");
    }
}
