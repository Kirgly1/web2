package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.User;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername (String username);

    List<User> findAllByUsername(String username);
}
