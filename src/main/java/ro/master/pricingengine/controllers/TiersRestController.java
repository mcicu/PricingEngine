package ro.master.pricingengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.master.pricingengine.beans.SimpleRestResponse;
import ro.master.pricingengine.beans.Tier;
import ro.master.pricingengine.services.TierService;

import java.util.List;

@RestController
public class TiersRestController extends AbstractRestController {

    @Autowired
    TierService tierService;

    @PostMapping(path = "/rest/tier/save", consumes = "application/json", produces = "application/json")
    public SimpleRestResponse saveTier(@RequestBody Tier tier) {
        tierService.saveTier(tier);
        return new SimpleRestResponse();
    }

    @GetMapping(value = "/rest/offers/{offerId}/tiers", produces = "application/json")
    public List<Tier> getTiersForOffer(@PathVariable("offerId") Long offerId) {
        return tierService.getTiersByOfferId(offerId);
    }
}
