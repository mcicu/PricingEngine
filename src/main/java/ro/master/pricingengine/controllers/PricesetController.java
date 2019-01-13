package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.beans.Priceset;
import ro.master.pricingengine.services.OfferService;
import ro.master.pricingengine.services.PricesetService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PricesetController {

    @Autowired
    PricesetService pricesetService;

    @Autowired
    OfferService offerService;

    @RequestMapping(path = "/pricesets")
    public String getPricesetList(Model model) {
        List<Priceset> pricesets = pricesetService.getPricesetList();
        model.addAttribute("pricesets", pricesets);
        return "pricesets/list";
    }

    @RequestMapping(path = "/pricesets/add")
    public String addPriceset(Model model) {
        List<Offer> offers = offerService.getOfferList();
        Priceset priceset = new Priceset();
        priceset.setStartDate(LocalDateTime.now().plusDays(1L).withHour(0).withMinute(0).withSecond(0));
        priceset.setEndDate(LocalDateTime.now().plusDays(1L).withHour(23).withMinute(59).withSecond(0));

        model.addAttribute("offers", offers);
        model.addAttribute("priceset", priceset);
        return "pricesets/add";
    }

    @RequestMapping(path = "/pricesets/{pricesetId}/edit")
    public String editPriceset(@PathVariable("pricesetId") Long pricesetId, Model model) {
        Priceset priceset = pricesetService.getPriceset(pricesetId);
        model.addAttribute("priceset", priceset);
        return "pricesets/edit";
    }
}
