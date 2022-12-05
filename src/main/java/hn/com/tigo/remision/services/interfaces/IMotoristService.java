package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.MotoristModel;

import java.util.List;

public interface IMotoristService {

    List<MotoristModel> getAll();
    MotoristModel getById(Long id);
    void add(MotoristModel model);
    void update(Long id, MotoristModel model);
    void delete(long id);

}
