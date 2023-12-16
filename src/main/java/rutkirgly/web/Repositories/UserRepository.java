package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.User;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
