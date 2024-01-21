package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.Model;

import java.util.List;
import java.util.UUID;
@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    //List<Model> findAllByOOrderByStartYearAsc(Pageable pageable);
    List<Model> findAllByBrandId(UUID brandId);
    @Query("SELECT m FROM Model m JOIN m.offers o WHERE o.id = :offerId")
    List<Model> getModelsByOfferId(@Param("offerId") UUID offerId);

}
