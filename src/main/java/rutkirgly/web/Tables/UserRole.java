package rutkirgly.web.Tables;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import rutkirgly.web.Tables.Abastracts.BaseEntity;
import rutkirgly.web.constants.Role;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseEntity {
    //    @Convert(converter = RoleConverter.class)
    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "role", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;


}
