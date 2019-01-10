package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.master.pricingengine.beans.Priceset;
import ro.master.pricingengine.services.PricesetService;

import java.util.List;

@Controller
public class PricesetController {

    @Autowired
    PricesetService pricesetService;

    @RequestMapping(path = "/pricesets")
    public String getPricesetList(Model model) {
        List<Priceset> pricesets = pricesetService.getPricesetList();
        model.addAttribute("pricesets", pricesets);
        return "pricesets/list";
    }

    @RequestMapping(path = "/pricesets/{pricesetId}/edit")
    public String editPriceset(@PathVariable("pricesetId") Long pricesetId, Model model) {
        Priceset priceset = pricesetService.getPriceset(pricesetId);
        model.addAttribute("priceset", priceset);
        return "pricesets/edit";
    }
}
