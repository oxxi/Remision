package hn.com.tigo.remision.services.interfaces;


import hn.com.tigo.remision.models.UnitOfMeasurementModel;

import java.util.List;

public interface IUnitService  {

    List<UnitOfMeasurementModel> getAll();
    UnitOfMeasurementModel getById(Long id);
    void update(Long id, UnitOfMeasurementModel model, String userName);
    void add(UnitOfMeasurementModel model, String userName);
    void delete(Long id);

}
