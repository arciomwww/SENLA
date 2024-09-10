CREATE TABLE UserRoles (
    user_id UUID,
    role_id UUID,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES "User"(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE
);