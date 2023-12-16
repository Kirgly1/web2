package rutkirgly.web.Tables;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rutkirgly.web.Tables.Abastracts.BaseEntity;
import rutkirgly.web.constants.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "UserRole")
public class UserRole extends BaseEntity {
    @NotNull(message = "role can't be null")
    @Column(nullable = false)
    private Role userRole;
}
