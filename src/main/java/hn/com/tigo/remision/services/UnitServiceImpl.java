package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.UnitOfMeasurementEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.repositories.remision.IUnitOfMeasurementRepository;
import hn.com.tigo.remision.services.interfaces.IUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UnitServiceImpl implements IUnitService {

    private final IUnitOfMeasurementRepository repository;

    public UnitServiceImpl(IUnitOfMeasurementRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<UnitOfMeasurementModel> getAll() {

      List<UnitOfMeasurementEntity> entities= this.repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
      return entities.stream().map(UnitOfMeasurementEntity::entityToModel).collect(Collectors.toList());

    }

    @Override
    public UnitOfMeasurementModel getById(Long id) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error get, Record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void update(Long id,UnitOfMeasurementModel model) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error update, Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setNombre(model.getName());
        entity.setUnidadEscalar(model.getUnitScalar());
        entity.setEstado(model.getStatus().toUpperCase());
        entity.setUsuarioEdita(model.getModifiedBy());
        entity.setFechaEdita(LocalDateTime.now());

        this.repository.saveAndFlush(entity);

    }

    @Override
    public void add(UnitOfMeasurementModel model) {
        UnitOfMeasurementEntity entity = new UnitOfMeasurementEntity();
        entity.setId(-1L);
        entity.setNombre(model.getName());
        entity.setUnidadEscalar(model.getUnitScalar());
        entity.setEstado(model.getStatus().toUpperCase());
        entity.setUsuarioCrea(model.getCreatedBy());
        entity.setFechaCreado(LocalDateTime.now());
        log.info("entidad {}", entity);
        this.repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));

        this.repository.delete(entity);
    }
}
