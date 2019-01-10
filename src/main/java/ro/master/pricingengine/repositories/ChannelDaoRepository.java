package ro.master.pricingengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.master.pricingengine.entities.ChannelDao;

@Repository
public interface ChannelDaoRepository extends JpaRepository<ChannelDao, String> {
}
