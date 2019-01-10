package ro.master.pricingengine.beans;

import java.time.LocalDateTime;

public class PriceCell {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long pricesetId;
    private Long productId;
    private Long tierId;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPricesetId() {
        return pricesetId;
    }

    public void setPricesetId(Long pricesetId) {
        this.pricesetId = pricesetId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTierId() {
        return tierId;
    }

    public void setTierId(Long tierId) {
        this.tierId = tierId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
