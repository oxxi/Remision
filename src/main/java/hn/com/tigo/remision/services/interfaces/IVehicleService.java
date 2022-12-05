package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.VehicleModel;

import java.util.List;

public interface IVehicleService {

    List<VehicleModel> getAll();

    VehicleModel getById(Long id);

    void add(VehicleModel model);

    void update(Long id, VehicleModel model);

    void delete(Long id);

}
