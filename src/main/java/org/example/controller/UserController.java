package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UserDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeUsers() {
        UserDTO user1 = new UserDTO();
        user1.setId(UUID.randomUUID());
        user1.setUsername("john_doe");
        user1.setPassword("password123");
        user1.setEmail("john.doe@example.com");
        user1.setFullName("John Doe");

        UserDTO user2 = new UserDTO();
        user2.setId(UUID.randomUUID());
        user2.setUsername("jane_doe");
        user2.setPassword("password456");
        user2.setEmail("jane.doe@example.com");
        user2.setFullName("Jane Doe");

        userService.createUser(user1);
        userService.createUser(user2);
    }

    @GetMapping
    public String getAllUsers() throws Exception {
        List<UserDTO> users = userService.getAllUsers();
        return objectMapper.writeValueAsString(users);
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable UUID id) throws Exception {
        UserDTO user = userService.getUserById(id);
        return objectMapper.writeValueAsString(user);
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeUsers();

        List<UserDTO> users = userService.getAllUsers();
        UserDTO userToRead = users.get(0);
        System.out.println("Read User: " + objectMapper.writeValueAsString(userService.getUserById(userToRead.getId())));

        UserDTO userToDelete = users.get(1);
        userService.deleteUser(userToDelete.getId());

        userToRead.setUsername("updated_username");
        userService.updateUser(userToRead.getId(), userToRead);

        System.out.println("Updated User: " + objectMapper.writeValueAsString(userService.getUserById(userToRead.getId())));
    }
}

