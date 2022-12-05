package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.GeneralParameter;

import java.util.List;

public interface IParameterService {

    List<GeneralParameter> getAll();

     GeneralParameter getById(String name);

    void add(GeneralParameter model);
    void update(String name, GeneralParameter model);


}
