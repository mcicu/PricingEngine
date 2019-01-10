package ro.master.pricingengine.beans;

import java.util.List;

public class MatrixRow {

    private Product product;
    private List<PriceCell> priceCells;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<PriceCell> getPriceCells() {
        return priceCells;
    }

    public void setPriceCells(List<PriceCell> priceCells) {
        this.priceCells = priceCells;
    }
}
