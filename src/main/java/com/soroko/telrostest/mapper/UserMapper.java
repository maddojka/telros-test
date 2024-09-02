package com.soroko.telrostest.mapper;


import com.soroko.telrostest.dto.UserDTO;
import com.soroko.telrostest.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * transform User data transfer object to User data and vise versa
 * @author yuriy.soroko
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);
}
