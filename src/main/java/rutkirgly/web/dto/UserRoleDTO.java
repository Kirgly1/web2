package rutkirgly.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRoleDTO {
    private UUID id;
    private String role;
    private UUID roleId;
    public UUID getId() {
        return id;
    }

    public UUID getRoleId() {
        return roleId;
    }
}
