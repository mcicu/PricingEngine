package ro.master.pricingengine.services;

import com.querydsl.core.types.dsl.BooleanExpression;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ro.master.pricingengine.beans.Tier;
import ro.master.pricingengine.entities.QTierDao;
import ro.master.pricingengine.entities.TierDao;
import ro.master.pricingengine.repositories.TierDaoRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TierService {

    @Autowired
    TierDaoRepository tierDaoRepository;

    @Autowired
    MapperFacade mapperFacade;

    public List<Tier> getTiersByOfferId(Long offerId) {
        BooleanExpression tierHasOfferId = QTierDao.tierDao.offerId.eq(offerId);
        Iterable<TierDao> tierDaoIterable = tierDaoRepository.findAll(tierHasOfferId);

        List<Tier> output = new ArrayList<>();
        for (TierDao tierDao : tierDaoIterable) {
            output.add(mapperFacade.map(tierDao, Tier.class));
        }
        return output;
    }

    public Tier getTier(Long tierId) {
        Optional<TierDao> tierDao = tierDaoRepository.findById(tierId);
        Assert.isTrue(tierDao.isPresent(), MessageFormat.format("Tier with id {0} was not found", tierId));

        return mapperFacade.map(tierDao.get(), Tier.class);
    }

    public void saveTier(Tier tier) {
        TierDao tierDao = new TierDao(); //assuming we have a new tier
        tierDao.setOfferId(tier.getOfferId());

        if (null != tier.getId()) {
            Optional<TierDao> optionalTierDao = tierDaoRepository.findById(tier.getId());
            Assert.isTrue(optionalTierDao.isPresent(), MessageFormat.format("Tier with id = {0} was not found", tier.getId()));
            tierDao = optionalTierDao.get();
        }
        tierDao.setName(tier.getName());

        tierDaoRepository.saveAndFlush(tierDao);
    }
}
