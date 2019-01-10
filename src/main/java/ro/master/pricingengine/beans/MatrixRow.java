package ro.master.pricingengine.beans;

import java.util.HashMap;
import java.util.List;

public class MatrixRow {

    private Product product;
    private HashMap<String, PriceCell>  priceCells; //key format: "priceTier{tierId}"

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public HashMap<String, PriceCell> getPriceCells() {
        return priceCells;
    }

    public void setPriceCells(HashMap<String, PriceCell> priceCells) {
        this.priceCells = priceCells;
    }
}
