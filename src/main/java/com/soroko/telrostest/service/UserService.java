package com.soroko.telrostest.service;

import com.soroko.telrostest.entity.User;
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

    /**
     * Get all users from database
     *
     * @return List of the available users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
     * @param user user to add
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Update user
     *
     * @param updatedUser  form of the user to edit
     * @param id id of the user to edit
     */
    public User updateUser(long id, User updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setMiddleName(updatedUser.getMiddleName());
        userToBeUpdated.setBirthDate(updatedUser.getBirthDate());
        userToBeUpdated.setContact(updatedUser.getContact());
//        userToBeUpdated.setImage(updatedUser.getImage());
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
