package ro.master.pricingengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.master.pricingengine.entities.PricesetDao;

import java.util.List;
import java.util.Set;

@Repository
public interface PricesetDaoRepository extends JpaRepository<PricesetDao, Long>, QuerydslPredicateExecutor<PricesetDao> {

    @Query(value = "select product_id from priceset_products where priceset_id = :pricesetId", nativeQuery = true)
    public Set<Long> fetchProductIdsForPriceset(@Param("pricesetId") Long pricesetId);
}
