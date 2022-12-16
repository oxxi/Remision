package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.ParameterEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.services.interfaces.IParameterService;
import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.repositories.remision.IParameterRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParameterServiceImpl implements IParameterService {

    private final IParameterRepository parameterRepository;

    public ParameterServiceImpl(IParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<GeneralParameter> getAll() {

       List<ParameterEntity> entities = this.parameterRepository.findAll(Sort.by(Sort.Direction.DESC,"modifiedAt"));
       return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());

    }



    @Override
    public GeneralParameter getById(String name) {

        ParameterEntity entity = this.parameterRepository.findByParameterName(name);
        if (entity==null) throw new BadRequestException(String.format("Record with id %s is not valid",name));
        return entity.entityToModel();
    }

    @Override
    public void add(GeneralParameter model) {
        ParameterEntity entity = new ParameterEntity();
        entity.setDescription(model.getDescription());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setModifiedAt(LocalDate.now());
        entity.setParameterName(model.getName());
        entity.setParameterValue(model.getValue());
        this.parameterRepository.save(entity);
    }

    @Override
    public void update(String name,GeneralParameter model) {
        ParameterEntity entity = this.parameterRepository.findByParameterName(name);
        if (entity==null) throw new BadRequestException(String.format("Record with id %s is not valid",name));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setParameterValue(model.getValue());
        entity.setDescription(model.getDescription());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setModifiedAt(LocalDate.now());
        this.parameterRepository.save(entity);
    }



}
