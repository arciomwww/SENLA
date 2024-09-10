package org.example.database;

import org.example.entity.UserRoles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRolesRepository {
    private final List<UserRoles> userRoles = new ArrayList<>();

    public UserRolesRepository() {
        //mock
        userRoles.add(new UserRoles.Builder()
                .setUserId(UUID.randomUUID())
                .setRoleId(UUID.randomUUID())
                .build());
        userRoles.add(new UserRoles.Builder()
                .setUserId(UUID.randomUUID())
                .setRoleId(UUID.randomUUID())
                .build());
    }

    public List<UserRoles> findAll() {
        return userRoles;
    }

    public UserRoles findByUserIdAndRoleId(UUID userId, UUID roleId) {
        return userRoles.stream()
                .filter(userRole -> userRole.getUserId().equals(userId) && userRole.getRoleId().equals(roleId))
                .findFirst()
                .orElse(null);
    }

    public void save(UserRoles userRole) {
        userRoles.add(userRole);
    }

    public void delete(UUID userId, UUID roleId) {
        userRoles.removeIf(userRole -> userRole.getUserId().equals(userId) && userRole.getRoleId().equals(roleId));
    }
}
