package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.UserRole;

import java.util.UUID;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}
