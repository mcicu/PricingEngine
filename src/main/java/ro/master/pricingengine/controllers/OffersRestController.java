package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.master.pricingengine.beans.Offer;
import ro.master.pricingengine.beans.SimpleRestResponse;
import ro.master.pricingengine.services.OfferService;

@RestController
public class OffersRestController extends AbstractRestController {

    @Autowired
    OfferService offerService;

    @PostMapping(value = "/rest/offer/save", consumes = "application/json", produces = "application/json")
    public SimpleRestResponse saveOffer(@RequestBody @Validated Offer offer) {
        offerService.saveOffer(offer);
        return new SimpleRestResponse();
    }
}
