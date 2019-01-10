package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.beans.SimpleRestResponse;
import ro.master.pricingengine.beans.Tier;
import ro.master.pricingengine.services.OfferService;

import java.util.List;

@RestController
public class OffersRestController extends AbstractRestController {

    @Autowired
    OfferService offerService;

    @PostMapping(value = "/rest/offer/save", consumes = "application/json", produces = "application/json")
    public SimpleRestResponse saveOffer(@RequestBody Offer offer) {
        offerService.saveOffer(offer);
        return new SimpleRestResponse();
    }
}
