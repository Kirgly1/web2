package rutkirgly.web.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private UserDTO role;
    private String imageUrl;
    private Date created;
    private Date modified;
}