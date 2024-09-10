package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UserRolesDTO;
import org.example.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-roles")
public class UserRolesController {
    private UserRolesService userRolesService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setUserRolesService(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeUserRoles() {
        UserRolesDTO role1 = new UserRolesDTO();
        role1.setUserId(UUID.randomUUID());
        role1.setRoleId(UUID.randomUUID());

        UserRolesDTO role2 = new UserRolesDTO();
        role2.setUserId(UUID.randomUUID());
        role2.setRoleId(UUID.randomUUID());

        userRolesService.createUserRole(role1);
        userRolesService.createUserRole(role2);
    }

    @GetMapping
    public String getAllUserRoles() throws Exception {
        List<UserRolesDTO> roles = userRolesService.getAllUserRoles();
        return objectMapper.writeValueAsString(roles);
    }

    @GetMapping("/{userId}/{roleId}")
    public String getUserRoleById(@PathVariable UUID userId, @PathVariable UUID roleId) throws Exception {
        UserRolesDTO role = userRolesService.getUserRoleById(userId, roleId);
        return objectMapper.writeValueAsString(role);
    }

    @PostMapping
    public void createUserRole(@RequestBody UserRolesDTO roleDTO) {
        userRolesService.createUserRole(roleDTO);
    }

    @PutMapping("/{userId}/{roleId}")
    public void updateUserRole(@PathVariable UUID userId, @PathVariable UUID roleId, @RequestBody UserRolesDTO roleDTO) {
        userRolesService.updateUserRole(userId, roleId, roleDTO);
    }

    @DeleteMapping("/{userId}/{roleId}")
    public void deleteUserRole(@PathVariable UUID userId, @PathVariable UUID roleId) {
        userRolesService.deleteUserRole(userId, roleId);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeUserRoles();

        List<UserRolesDTO> roles = userRolesService.getAllUserRoles();
        UserRolesDTO roleToRead = roles.get(0);
        System.out.println("Read UserRole: " + objectMapper.writeValueAsString(userRolesService.getUserRoleById(roleToRead.getUserId(), roleToRead.getRoleId())));

        UserRolesDTO roleToDelete = roles.get(1);
        userRolesService.deleteUserRole(roleToDelete.getUserId(), roleToDelete.getRoleId());

        UUID oldRoleId = roleToRead.getRoleId();
        roleToRead.setRoleId(UUID.randomUUID());
        userRolesService.updateUserRole(roleToRead.getUserId(), oldRoleId, roleToRead);

        System.out.println("Updated UserRole: " + objectMapper.writeValueAsString(userRolesService.getUserRoleById(roleToRead.getUserId(), roleToRead.getRoleId())));
    }
}
