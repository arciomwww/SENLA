package org.example.database;

import org.example.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RoleRepository {
    private final List<Role> roles = new ArrayList<>();

    public RoleRepository() {
        //mock
        roles.add(new Role.Builder()
                .setId(UUID.randomUUID())
                .setName("Admin")
                .build());
        roles.add(new Role.Builder()
                .setId(UUID.randomUUID())
                .setName("User")
                .build());
    }

    public List<Role> findAll() {
        return roles;
    }

    public Role findById(UUID id) {
        return roles.stream().filter(role -> role.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Role role) {
        roles.add(role);
    }

    public void delete(UUID id) {
        roles.removeIf(role -> role.getId().equals(id));
    }
}