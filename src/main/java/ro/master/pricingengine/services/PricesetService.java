package ro.master.pricingengine.services;

import com.querydsl.core.types.Predicate;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ro.master.pricingengine.beans.MatrixRow;
import ro.master.pricingengine.beans.Priceset;
import ro.master.pricingengine.entities.*;
import ro.master.pricingengine.repositories.PriceCellDaoRepository;
import ro.master.pricingengine.repositories.PricesetDaoRepository;
import ro.master.pricingengine.repositories.ProductDaoRepository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PricesetService {

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
        Set<Long> productIds = pricesetDaoRepository.fetchProductIdsForPriceset(pricesetId);

        productIds.parallelStream().forEach(productId -> {
            ProductDao productDao = productDaoRepository.findById(productId).get();
            List<PriceCellDao> priceCells = fetchPriceCellsByPricesetIdAndProductId(pricesetId, productId);

            MatrixRowDao row = new MatrixRowDao();
            row.setProductDao(productDao);
            row.setPriceCells(priceCells);
            output.add(mapperFacade.map(row, MatrixRow.class));
        });

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
}
