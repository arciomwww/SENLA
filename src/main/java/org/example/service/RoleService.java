package org.example.service;

import org.example.dto.RoleDTO;
import org.example.database.RoleRepository;
import org.example.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public RoleDTO getRoleById(UUID id) {
        Role role = roleRepository.findById(id);
        return role != null ? convertToDTO(role) : null;
    }

    public void createRole(RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        roleRepository.save(role);
    }

    public void updateRole(UUID id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id);
        if (role != null) {
            role.setName(roleDTO.getName());
            roleRepository.save(role);
        }
    }

    public void deleteRole(UUID id) {
        roleRepository.delete(id);
    }

    private RoleDTO convertToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    private Role convertToEntity(RoleDTO roleDTO) {
        return new Role.Builder()
                .setId(roleDTO.getId())
                .setName(roleDTO.getName())
                .build();
    }
}
