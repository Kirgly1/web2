package rutkirgly.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRoleDTO {
    private UUID id;
    private String role;
}
