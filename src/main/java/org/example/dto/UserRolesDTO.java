package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class UserRolesDTO {
    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("role_id")
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
}