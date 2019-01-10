package ro.master.pricingengine.services;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.entities.ChannelDao;
import ro.master.pricingengine.entities.OfferDao;
import ro.master.pricingengine.repositories.ChannelDaoRepository;
import ro.master.pricingengine.repositories.OfferDaoRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private static MapperFacade mapperFacade;

    static {
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(OfferDao.class, Offer.class)
                .field("channels{id}", "channels{}")
                .byDefault()
                .register();
        mapperFacade = factory.getMapperFacade();
    }

    @Autowired
    OfferDaoRepository offerDaoRepository;

    @Autowired
    ChannelDaoRepository channelDaoRepository;

    public List<Offer> getOfferList() {
        List<OfferDao> offers = offerDaoRepository.findAll();
        List<Offer> result = offers.stream()
                .map(offerDao -> mapperFacade.map(offerDao, Offer.class))
                .collect(Collectors.toList());
        return result;
    }

    public Offer getOffer(Long id) {
        Optional<OfferDao> optionalOfferDao = offerDaoRepository.findById(id);
        Assert.isTrue(optionalOfferDao.isPresent(), MessageFormat.format("Offer with id = {0} was not found", id));

        Offer result = mapperFacade.map(optionalOfferDao.get(), Offer.class);
        return result;
    }

    public void saveOffer(Offer offer) {
        Assert.notNull(offer.getId(), "Error: Trying to save an offer with null id");

        Optional<OfferDao> optionalOfferDao = offerDaoRepository.findById(offer.getId());
        Assert.isTrue(optionalOfferDao.isPresent(), MessageFormat.format("Offer with id = {0} was not found", offer.getId()));

        List<ChannelDao> channelDaos = CollectionUtils.isEmpty(offer.getChannels()) ? new ArrayList<>() : channelDaoRepository.findAllById(offer.getChannels());

        OfferDao offerDao = optionalOfferDao.get();
        offerDao.setName(offer.getName());
        offerDao.setDescription(offer.getDescription());
        offerDao.setChannels(channelDaos);
        offerDaoRepository.saveAndFlush(offerDao);
    }
}
