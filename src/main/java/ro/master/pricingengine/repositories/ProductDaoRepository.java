package ro.master.pricingengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.master.pricingengine.entities.ProductDao;

@Repository
public interface ProductDaoRepository extends JpaRepository<ProductDao, Long> {
}
