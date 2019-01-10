package ro.master.pricingengine.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRICE_SETS")
public class PricesetDao {

    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OfferDao offer;
    private List<Long> productIds = new ArrayList<>();

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_date")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    public OfferDao getOffer() {
        return offer;
    }

    public void setOffer(OfferDao offer) {
        this.offer = offer;
    }


    @ElementCollection(fetch = FetchType.EAGER, targetClass = Long.class)
    @CollectionTable(name = "priceset_products", joinColumns = {@JoinColumn(name = "priceset_id")})
    @Column(name = "product_id")
    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
