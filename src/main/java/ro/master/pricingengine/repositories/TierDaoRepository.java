package ro.master.pricingengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ro.master.pricingengine.entities.TierDao;

@Repository
public interface TierDaoRepository extends JpaRepository<TierDao, Long>, QuerydslPredicateExecutor<TierDao> {
}
