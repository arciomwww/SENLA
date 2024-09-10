package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.RoleDTO;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeRoles() {
        RoleDTO role1 = new RoleDTO();
        role1.setId(UUID.randomUUID());
        role1.setName("Admin");

        RoleDTO role2 = new RoleDTO();
        role2.setId(UUID.randomUUID());
        role2.setName("User");

        roleService.createRole(role1);
        roleService.createRole(role2);
    }

    @GetMapping
    public String getAllRoles() throws Exception {
        List<RoleDTO> roles = roleService.getAllRoles();
        return objectMapper.writeValueAsString(roles);
    }

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable UUID id) throws Exception {
        RoleDTO role = roleService.getRoleById(id);
        return objectMapper.writeValueAsString(role);
    }

    @PostMapping
    public void createRole(@RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable UUID id, @RequestBody RoleDTO roleDTO) {
        roleService.updateRole(id, roleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeRoles();

        List<RoleDTO> roles = roleService.getAllRoles();
        RoleDTO roleToRead = roles.get(0);
        System.out.println("Read Role: " + objectMapper.writeValueAsString(roleService.getRoleById(roleToRead.getId())));

        RoleDTO roleToDelete = roles.get(1);
        roleService.deleteRole(roleToDelete.getId());

        roleToRead.setName("UPDATED ROLE");
        roleService.updateRole(roleToRead.getId(), roleToRead);

        System.out.println("Updated Role: " + objectMapper.writeValueAsString(roleService.getRoleById(roleToRead.getId())));
    }
}
