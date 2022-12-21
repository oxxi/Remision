package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.as400.TestAs400Entity;
import hn.com.tigo.remision.entities.remision.ParameterEntity;
import hn.com.tigo.remision.models.ProductModel;
import hn.com.tigo.remision.repositories.as400.CustomV1ProductDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    CustomV1ProductDaoImpl customV1ProductDao;

    private TestAs400Entity entity;

    @BeforeEach
    public void setUp() {

        entity = new TestAs400Entity();
        entity.setColumn1(new BigDecimal(2));
        entity.setColumn2("test");
        entity.setColumn3("test");
    }


    @Test
    void getAll() {
        List<TestAs400Entity> returnData = Arrays.asList(entity);
        given(customV1ProductDao.listProduct()).willReturn(returnData);

        List<ProductModel> data = productService.getAll();

        assertEquals(1L,data.size());
    }
}