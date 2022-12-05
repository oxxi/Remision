package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.LocationModel;

import java.util.List;

public interface ILocationService {
    List<LocationModel> getAll();
    LocationModel getById(Long id);
    void add(LocationModel model);
    void update(Long id,LocationModel model);
    void delete(Long id);
}
