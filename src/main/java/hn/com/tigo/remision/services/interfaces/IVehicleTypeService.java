package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.VehicleTypesModel;

import java.util.List;

public interface IVehicleTypeService {

    List<VehicleTypesModel> getAll();

    VehicleTypesModel getById(Long id);

    void add(VehicleTypesModel model);

    void update(Long id, VehicleTypesModel model);

    void delete(Long id);

}
