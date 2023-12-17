package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.Model;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    //List<Model> findAllByOOrderByStartYearAsc(Pageable pageable);
    List<Model> findAllByBrandId(UUID brandId);
}
