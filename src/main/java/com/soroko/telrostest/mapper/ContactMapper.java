package com.soroko.telrostest.mapper;

import com.soroko.telrostest.dto.ContactDTO;
import com.soroko.telrostest.entity.Contact;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * transform Contact data transfer object to Contact data and vise versa
 * @author yuriy.soroko
 */
@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDTO toContactDTO(Contact contact);

    Contact toContact(ContactDTO contactDTO);

    List<ContactDTO> toContactDTOList(List<Contact> contacts);
}
