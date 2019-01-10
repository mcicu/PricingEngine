package ro.master.pricingengine.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE_CELLS")
public class PriceCellDao {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long pricesetId;
    private Long productId;
    private Long tierId;
    private Double value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Column(name = "priceset_id")
    public Long getPricesetId() {
        return pricesetId;
    }

    public void setPricesetId(Long pricesetId) {
        this.pricesetId = pricesetId;
    }

    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "tier_id")
    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }

    @Column(name = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
