package rutkirgly.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rutkirgly.web.Tables.Brand;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;
@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    @Query("SELECT b FROM Brand b LEFT JOIN b.models m GROUP BY b ORDER BY COUNT(m) DESC")
    List<Brand> findMostPopular(Pageable pageable);

}
