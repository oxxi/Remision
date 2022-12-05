package hn.com.tigo.remision.repositories.as400;

import hn.com.tigo.remision.entities.as400.TestAs400Entity;

import java.util.List;

public interface ICustomV1ProductDao {

    void addProduct(TestAs400Entity testAs400Entity);

    void deleteProduct(TestAs400Entity testAs400Entity);

    void editProduct(TestAs400Entity testAs400Entity);

    List<TestAs400Entity> listProduct();

    TestAs400Entity getBy();

}
