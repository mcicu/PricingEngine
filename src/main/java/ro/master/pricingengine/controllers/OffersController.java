package ro.master.pricingengine.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.master.pricingengine.beans.Channel;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.services.ChannelService;
import ro.master.pricingengine.services.OfferService;
import ro.master.pricingengine.utils.JsonUtils;

import java.util.List;

@Controller
public class OffersController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OfferService offerService;

    @Autowired
    ChannelService channelService;

    @RequestMapping("/offers")
    public String offers(Model model) {
        List<Offer> offers = offerService.getOfferList();
        model.addAttribute("offers", offers);
        logger.info("OFFERS {}", JsonUtils.toJson(offers));
        return "offers/list";
    }

    @RequestMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable("id") Long id, Model model) {
        Offer offer = offerService.getOffer(id);
        logger.info("offer {}", JsonUtils.toJson(offer));
        List<Channel> channels = channelService.getChannelList();
        model.addAttribute("offer", offer);
        model.addAttribute("channels", channels);
        return "offers/edit";
    }
}
