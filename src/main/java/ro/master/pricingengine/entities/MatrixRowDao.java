package ro.master.pricingengine.entities;

import java.util.List;

//wrapper
public class MatrixRowDao {

    private ProductDao productDao;
    private List<PriceCellDao> priceCells;

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<PriceCellDao> getPriceCells() {
        return priceCells;
    }

    public void setPriceCells(List<PriceCellDao> priceCells) {
        this.priceCells = priceCells;
    }
}
