package hn.com.tigo.remision.services.interfaces;


import hn.com.tigo.remision.models.UnitOfMeasurementModel;

import java.util.List;

public interface IUnitService  {

    List<UnitOfMeasurementModel> getAll();
    UnitOfMeasurementModel getById(Long id);
    void update(Long id, UnitOfMeasurementModel model);
    void add(UnitOfMeasurementModel model);
    void delete(Long id);

}
