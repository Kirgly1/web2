package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.Offers;

import java.util.List;
import java.util.UUID;

@Repository
public interface OffersRepository extends JpaRepository<Offers, UUID> {
    List<Offers> findAllByModelId(UUID modelId);
    List<Offers> getAllByModelId(UUID modelId);
}
