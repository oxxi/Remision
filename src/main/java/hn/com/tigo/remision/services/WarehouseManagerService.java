package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.WarehouseManagerEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.WarehouseManagerModel;
import hn.com.tigo.remision.repositories.remision.IWarehouseManagerRepository;
import hn.com.tigo.remision.services.interfaces.IWarehouseManagerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseManagerService implements IWarehouseManagerService {

    private final IWarehouseManagerRepository managerRepository;

    public WarehouseManagerService(IWarehouseManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }


    @Override
    public List<WarehouseManagerModel> getAll() {
        List<WarehouseManagerEntity> entities = this.managerRepository.findAll();
        return entities.stream().map(x->x.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public WarehouseManagerModel getById(Long id) {
        WarehouseManagerEntity entity = this.managerRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void add(WarehouseManagerModel model) {
        WarehouseManagerEntity entity = new WarehouseManagerEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setName(model.getName());
        entity.setLastName(model.getLastName());
        entity.setDni(model.getDni());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedAt(LocalDate.now());
        entity.setStatus(model.getStatus());
        this.managerRepository.save(entity);
    }

    @Override
    public void update(Long id, WarehouseManagerModel model) {
        WarehouseManagerEntity entity = this.managerRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setName(model.getName());
        entity.setLastName(model.getLastName());
        entity.setDni(model.getDni());
        entity.setStatus(model.getStatus() == null ? entity.getStatus():model.getStatus());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setModifiedAt(LocalDate.now());
        this.managerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        WarehouseManagerEntity entity = this.managerRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        this.managerRepository.delete(entity);
    }
}
