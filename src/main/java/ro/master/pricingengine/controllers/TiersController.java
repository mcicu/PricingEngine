package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.beans.Tier;
import ro.master.pricingengine.services.OfferService;
import ro.master.pricingengine.services.TierService;

import java.util.List;

@Controller
public class TiersController {

    @Autowired
    TierService tierService;

    @Autowired
    OfferService offerService;

    @RequestMapping("/offers/{offerId}/tiers")
    public String offerTiers(@PathVariable("offerId") Long offerId, Model model) {
        Offer offer = offerService.getOffer(offerId);
        List<Tier> tiers = tierService.getTiersByOfferId(offerId);
        model.addAttribute("offer", offer);
        model.addAttribute("tiers", tiers);

        return "tiers/list";
    }

    @RequestMapping("/offers/{offerId}/tiers/add")
    public String addOfferTier(@PathVariable("offerId") Long offerId, Model model) {
        Offer offer = offerService.getOffer(offerId);
        Tier tier = new Tier();
        model.addAttribute("isNewTier", true);
        model.addAttribute("offer", offer);
        model.addAttribute("tier", tier);

        return "tiers/edit";
    }

    @RequestMapping("/offers/{offerId}/tiers/{tierId}/edit")
    public String offerTiers(@PathVariable("offerId") Long offerId, @PathVariable("tierId") Long tierId, Model model) {
        Offer offer = offerService.getOffer(offerId);
        Tier tier = tierService.getTier(tierId);
        model.addAttribute("offer", offer);
        model.addAttribute("tier", tier);

        return "tiers/edit";
    }
}
