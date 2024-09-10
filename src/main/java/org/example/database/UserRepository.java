package org.example.database;

import org.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        //mock
        users.add(new User.Builder()
                .setId(UUID.randomUUID())
                .setUsername("john_doe")
                .setPassword("password123")
                .setEmail("john.doe@example.com")
                .setFullName("John Doe")
                .build());
        users.add(new User.Builder()
                .setId(UUID.randomUUID())
                .setUsername("jane_doe")
                .setPassword("password456")
                .setEmail("jane.doe@example.com")
                .setFullName("Jane Doe")
                .build());
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(UUID id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(User user) {
        users.add(user);
    }

    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
