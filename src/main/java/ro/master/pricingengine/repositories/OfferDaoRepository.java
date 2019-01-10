package ro.master.pricingengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.master.pricingengine.entities.OfferDao;

@Repository
public interface OfferDaoRepository extends JpaRepository<OfferDao, Long> {
}
