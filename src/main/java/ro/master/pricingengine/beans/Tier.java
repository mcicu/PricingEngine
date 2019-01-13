package ro.master.pricingengine.beans;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class Tier {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Long offerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
}
