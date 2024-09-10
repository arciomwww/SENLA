package org.example.service;

import org.example.dto.UserDTO;
import org.example.database.UserRepository;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id);
        return user != null ? convertToDTO(user) : null;
    }

    public void createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        userRepository.save(user);
    }

    public void updateUser(UUID id, UserDTO userDTO) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setFullName(userDTO.getFullName());
            userRepository.save(user);
        }
    }

    public void deleteUser(UUID id) {
        userRepository.delete(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User.Builder()
                .setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword())
                .setEmail(userDTO.getEmail())
                .setFullName(userDTO.getFullName())
                .build();
    }
}

