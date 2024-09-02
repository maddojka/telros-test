package com.soroko.telrostest.controller;

import com.soroko.telrostest.dto.UserDTO;
import com.soroko.telrostest.entity.User;
import com.soroko.telrostest.mapper.UserMapper;
import com.soroko.telrostest.service.UserService;
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
 * This class consists REST API logic of users
 * @author yuriy.soroko
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(
        name = "Users",
        description = "All methods to work with users data of the system"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;
    final UserMapper userMapper;

    /**
     * Get all users from the service
     *
     * @return returns list of all modified users by mapper
     */
    @GetMapping("/all")
    @Operation(summary = "Get information about all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> usersDTO = userMapper.toUserDTOList(users);
        return ResponseEntity.ok(usersDTO);
    }

    /**
     * Get user by id
     *
     * @param id id of the user
     * @return returns user data
     */
    @GetMapping("/get")
    @Operation(summary = "Get information about user by id")
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "user id", example = "1")
            @RequestParam int id) {
        var user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        UserDTO userDTO = userMapper.toUserDTO(user);
        return ResponseEntity.ok(userDTO);

    }

    /**
     * Add user to the system
     *
     * @param userDTO user that is required to add in the system
     * @return returns user which was added
     */
    @PostMapping("/add")
    @Operation(summary = "Add user to the system")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        userService.addUser(user);
        log.info("Adding new user : {}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param userDTO user that is required to edit in the system
     * @return returns user which was edited
     */
    @PatchMapping("/edit")
    @Operation(summary = "Edit existing user of the system")
    public ResponseEntity<User> updateUser(@RequestParam long id, @RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        userService.updateUser(id, user);
        log.info("Updating user : {}", user);
        return ResponseEntity.ok(user);
    }

    /**
     * @param id id of the user that is required to delete from the system
     */
    @DeleteMapping("/delete")
    @Operation(summary = "Delete user from the system")
    public void deleteUser(
            @Parameter(description = "user id", example = "1")
            @RequestParam long id) {
        userService.deleteUser(id);
    }
}
