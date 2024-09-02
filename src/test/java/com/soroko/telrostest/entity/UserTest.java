package com.soroko.telrostest.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class UserTest {

    @Test
    @DisplayName("Check user is OK")
    public void User_isOk() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setMiddleName("Ivanovich");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setContact(new Contact());
        user.setImage(new Image());
    }
}
