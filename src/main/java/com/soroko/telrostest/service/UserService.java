package com.soroko.telrostest.service;

import com.soroko.telrostest.dto.UserDTO;
import com.soroko.telrostest.entity.User;
import com.soroko.telrostest.mapper.UserMapper;
import com.soroko.telrostest.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * This class consists logic of user data
 *
 * @author yuriy.soroko
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    /**
     * inject user repository in order to put and get data from database
     */
    final UserRepository userRepository;
    final UserMapper userMapper;

    /**
     * Get all users from database
     *
     * @return List of the available users
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOList(users);
    }


    /**
     * Get user by id
     *
     * @param id - id of the user to get
     * @return user as requested User
     */
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * Add user to database
     *
     * @param userDTO user to add
     */
    public User addUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userRepository.save(user);
    }

    /**
     * Update user
     *
     * @param updatedUser  form of the user to edit
     * @param id id of the user to edit
     */
    public User updateUser(long id, UserDTO updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setFirstName(updatedUser.firstName());
        userToBeUpdated.setLastName(updatedUser.lastName());
        userToBeUpdated.setMiddleName(updatedUser.middleName());
        userToBeUpdated.setBirthDate(updatedUser.birthDate());
        userToBeUpdated.setContact(updatedUser.contact());
        return userRepository.save(userToBeUpdated);
    }

    /**
     * Delete user by id
     *
     * @param id - id of the user to remove from the database
     */
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


}
