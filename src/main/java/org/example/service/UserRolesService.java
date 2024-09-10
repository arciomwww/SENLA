package org.example.service;

import org.example.dto.UserRolesDTO;
import org.example.database.UserRolesRepository;
import org.example.entity.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRolesService {
    private UserRolesRepository userRolesRepository;

    @Autowired
    public void setUserRolesRepository(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    public List<UserRolesDTO> getAllUserRoles() {
        return userRolesRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserRolesDTO getUserRoleById(UUID userId, UUID roleId) {
        UserRoles userRole = userRolesRepository.findByUserIdAndRoleId(userId, roleId);
        return userRole != null ? convertToDTO(userRole) : null;
    }

    public void createUserRole(UserRolesDTO userRolesDTO) {
        UserRoles userRole = convertToEntity(userRolesDTO);
        userRolesRepository.save(userRole);
    }

    public void updateUserRole(UUID userId, UUID oldRoleId, UserRolesDTO userRolesDTO) {
        UserRoles userRole = userRolesRepository.findByUserIdAndRoleId(userId, oldRoleId);
        if (userRole != null) {
            userRole.setUserId(userRolesDTO.getUserId());
            userRole.setRoleId(userRolesDTO.getRoleId());
            userRolesRepository.save(userRole);
        }
    }

    public void deleteUserRole(UUID userId, UUID roleId) {
        userRolesRepository.delete(userId, roleId);
    }

    private UserRolesDTO convertToDTO(UserRoles userRole) {
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        userRolesDTO.setUserId(userRole.getUserId());
        userRolesDTO.setRoleId(userRole.getRoleId());
        return userRolesDTO;
    }

    private UserRoles convertToEntity(UserRolesDTO userRolesDTO) {
        return new UserRoles.Builder()
                .setUserId(userRolesDTO.getUserId())
                .setRoleId(userRolesDTO.getRoleId())
                .build();
    }
}
