package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.WarehouseManagerModel;

import java.util.List;

public interface IWarehouseManagerService {

    List<WarehouseManagerModel> getAll();
    WarehouseManagerModel getById(Long id);
    void add(WarehouseManagerModel model);
    void update(Long id,WarehouseManagerModel model);
    void delete(Long id);
}
