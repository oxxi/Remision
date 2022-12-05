package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.as400.TestAs400Entity;
import hn.com.tigo.remision.models.ProductModel;
import hn.com.tigo.remision.repositories.as400.CustomV1ProductDaoImpl;
import hn.com.tigo.remision.services.interfaces.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final CustomV1ProductDaoImpl customV1ProductDao;

    public ProductServiceImpl(CustomV1ProductDaoImpl customV1ProductDao) {
        this.customV1ProductDao = customV1ProductDao;
    }


    @Override
    public List<ProductModel> getAll() {
       List<TestAs400Entity> entities = this.customV1ProductDao.listProduct();
       log.info("Services data size: "+entities.size());
       return entities.stream().map(x->{
            ProductModel model = new ProductModel();
            model.setColumna1(String.valueOf(x.getColumn1()));
            model.setColumna2(x.getColumn2());
            model.setColumna3(x.getColumn3());
            return model;
        }).collect(Collectors.toList());

    }
}
