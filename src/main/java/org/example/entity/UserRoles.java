package org.example.entity;

import java.util.UUID;

public class UserRoles {
    private UUID userId;
    private UUID roleId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public static class Builder {
        private UUID userId;
        private UUID roleId;

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRoleId(UUID roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserRoles build() {
            UserRoles userRoles = new UserRoles();
            userRoles.userId = this.userId;
            userRoles.roleId = this.roleId;
            return userRoles;
        }
    }
}
