package ro.master.pricingengine.services;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.master.pricingengine.beans.Channel;
import ro.master.pricingengine.repositories.ChannelDaoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelService {

    @Autowired
    ChannelDaoRepository channelDaoRepository;

    @Autowired
    MapperFacade mapperFacade;

    public List<Channel> getChannelList() {
        return channelDaoRepository.findAll().stream()
                .map(channelDao -> mapperFacade.map(channelDao, Channel.class))
                .collect(Collectors.toList());

    }
}
