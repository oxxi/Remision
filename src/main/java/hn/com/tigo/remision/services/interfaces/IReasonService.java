package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.ReasonModel;

import java.util.List;

public interface IReasonService {

    List<ReasonModel> getAll();
    ReasonModel getById(Long id);
    void add(ReasonModel model);
    void update(Long id, ReasonModel model);
    void delete(Long id);


}
