package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.TransportAgencyModel;

import java.util.List;

public interface ITransportAgencyService {
    List<TransportAgencyModel> getAll();
    TransportAgencyModel getById(Long id);
    void add(TransportAgencyModel model);
    void update(Long id,TransportAgencyModel model);
    void delete(Long id);

}
