package com.soroko.telrostest.repository;

import com.soroko.telrostest.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This class consists SQL queries to create, read, update or delete contact
 * from database
 *
 * @author yuriy.soroko
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
