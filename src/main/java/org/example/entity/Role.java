package org.example.entity;

import java.util.UUID;

public class Role {
        private UUID id;
        private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private UUID id;
        private String name;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.id = this.id;
            role.name = this.name;
            return role;
        }
    }
}
