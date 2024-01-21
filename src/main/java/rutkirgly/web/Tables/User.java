package rutkirgly.web.Tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rutkirgly.web.Tables.Abastracts.BaseEx;
import rutkirgly.web.constants.Role;

import java.util.Set;

@Entity(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Users")
public class User extends BaseEx {


    @NotBlank(message = "Username can't be empty")
    @Size(min = 3, max = 24, message = "username can't be less than 3 and can't be more than 24")
    private String username;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, max = 16, message = "password can't be less than 8 and can't be more than 16")
    private String password;

    @NotBlank(message = "FirstName can't be empty")
    @Size(min = 3, max = 12, message = "firstName can't be less than 3 and can't be more than 12")
    private String firstName;

    @NotBlank(message = "lastName can't be empty")
    @Size(min = 3, max = 12, message = "lastName can't be less than 3 and can't be more than 12")
    private String lastName;

    @NotNull
    private Boolean isActive;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private UserRole role;

    @Pattern(regexp = "^(http|https)://.*\\.(jpg|png|gif|bmp)$", message = "Image URL must be a valid image URL")
    private String imageUrl;
    public Set<String> getRoles() {
        return (Set<String>) role;
    }

    public void setRoles(Set<Role> roles) {
        this.role = role;
    }
}
