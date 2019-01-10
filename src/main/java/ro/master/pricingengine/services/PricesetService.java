package ro.master.pricingengine.services;

import com.querydsl.core.types.Predicate;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ro.master.pricingengine.beans.MatrixRow;
import ro.master.pricingengine.beans.PriceCell;
import ro.master.pricingengine.beans.Priceset;
import ro.master.pricingengine.beans.Product;
import ro.master.pricingengine.entities.PriceCellDao;
import ro.master.pricingengine.entities.PricesetDao;
import ro.master.pricingengine.entities.ProductDao;
import ro.master.pricingengine.entities.QPriceCellDao;
import ro.master.pricingengine.repositories.PriceCellDaoRepository;
import ro.master.pricingengine.repositories.PricesetDaoRepository;
import ro.master.pricingengine.repositories.ProductDaoRepository;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PricesetService {

    Logger logger = LoggerFactory.getLogger(PricesetService.class);

    @Autowired
    PricesetDaoRepository pricesetDaoRepository;

    @Autowired
    PriceCellDaoRepository priceCellDaoRepository;

    @Autowired
    ProductDaoRepository productDaoRepository;

    @Autowired
    MapperFacade mapperFacade;

    public List<Priceset> getPricesetList() {
        return pricesetDaoRepository.findAll().stream()
                .map(pricesetDao -> mapperFacade.map(pricesetDao, Priceset.class))
                .collect(Collectors.toList());
    }

    public Priceset getPriceset(Long id) {
        Optional<PricesetDao> optionalPricesetDao = pricesetDaoRepository.findById(id);
        Assert.isTrue(optionalPricesetDao.isPresent(), MessageFormat.format("Priceset with id = {0} was not found", id));

        Priceset result = mapperFacade.map(optionalPricesetDao.get(), Priceset.class);
        return result;
    }

    public List<MatrixRow> getMatrixRows(Long pricesetId) {
        List<MatrixRow> output = new ArrayList<>();
        Set<Integer> productIds = pricesetDaoRepository.fetchProductIdsForPriceset(pricesetId);

        for (Integer productId : productIds) {
            ProductDao productDao = productDaoRepository.findById(productId.longValue()).get();
            List<PriceCellDao> priceCellDaos = fetchPriceCellsByPricesetIdAndProductId(pricesetId, productId.longValue());

            HashMap<String, PriceCell> priceCells = (HashMap<String, PriceCell>) priceCellDaos.stream()
                    .map(pc -> mapperFacade.map(pc, PriceCell.class))
                    .collect(Collectors.toMap((PriceCell cell) -> "priceTier" + cell.getTierId(), Function.identity()));

            MatrixRow row = new MatrixRow();
            row.setProduct(mapperFacade.map(productDao, Product.class));
            row.setPriceCells(priceCells);
            output.add(row);
        }

        return output;
    }

    private List<PriceCellDao> fetchPriceCellsByPricesetIdAndProductId(Long pricesetId, Long productId) {
        Predicate predicate = QPriceCellDao.priceCellDao.pricesetId.eq(pricesetId)
                .and(QPriceCellDao.priceCellDao.productId.eq(productId));
        Iterable<PriceCellDao> priceCellDaoIterable = priceCellDaoRepository.findAll(predicate);

        List<PriceCellDao> output = new ArrayList<>();
        priceCellDaoIterable.forEach(output::add);
        return output;
    }

    public void savePriceCells(List<PriceCell> priceCells) {
        for (PriceCell cell : priceCells) {
            if (null != cell.getId()) {
                PriceCellDao priceCellDao = priceCellDaoRepository.findById(cell.getId()).get();
                priceCellDao.setValue(cell.getValue());
                priceCellDaoRepository.save(priceCellDao);
            }
            else {
                PriceCellDao priceCellDao = new PriceCellDao();
                priceCellDao.setValue(cell.getValue());
                priceCellDao.setTierId(cell.getTierId());
                priceCellDao.setPricesetId(cell.getPricesetId());
                priceCellDao.setProductId(cell.getProductId());
                priceCellDaoRepository.save(priceCellDao);
            }
        }

        priceCellDaoRepository.flush();
    }
}
