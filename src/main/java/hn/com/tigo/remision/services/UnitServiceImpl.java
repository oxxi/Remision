package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.UnitOfMeasurementEntity;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.repositories.remision.IUnitOfMeasurementRepository;
import hn.com.tigo.remision.services.interfaces.IUnitService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements IUnitService {

    private final IUnitOfMeasurementRepository repository;

    public UnitServiceImpl(IUnitOfMeasurementRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<UnitOfMeasurementModel> getAll() {

      List<UnitOfMeasurementEntity> entities= this.repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
      return entities.stream().map(x->{
          UnitOfMeasurementModel model = new UnitOfMeasurementModel();
          model.setId(x.getId());
          model.setName(x.getNombre());
          model.setStatus(String.valueOf(x.getEstado()));
          model.setUnitScalar(x.getUnidadEscalar());
          model.setModifiedBy(x.getUsuarioEdita());
          model.setCreateBy(x.getUsuarioCrea());
          model.setCreateAt(x.getFechaCreado());
          model.setModifiedAt(x.getFechaEdita());
          return model;
      }).collect(Collectors.toList());

    }

    @Override
    public UnitOfMeasurementModel getById(Long id) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Error");

        UnitOfMeasurementModel model = new UnitOfMeasurementModel();
        model.setId(entity.getId());
        model.setName(entity.getNombre());
        model.setStatus(String.valueOf(entity.getEstado()));
        model.setUnitScalar(entity.getUnidadEscalar());
        model.setModifiedBy(entity.getUsuarioEdita());
        model.setCreateBy(entity.getUsuarioCrea());
        model.setCreateAt(entity.getFechaCreado());
        model.setModifiedAt(entity.getFechaEdita());
        return model;
    }

    @Override
    public void update(Long id,UnitOfMeasurementModel model, String userName) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Error");
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setNombre(model.getName());
        entity.setUnidadEscalar(model.getUnitScalar());
        entity.setEstado(model.getStatus().toUpperCase().charAt(0));
        entity.setUsuarioEdita(userName);
        entity.setFechaEdita(LocalDateTime.now());

        this.repository.saveAndFlush(entity);

    }

    @Override
    public void add(UnitOfMeasurementModel model, String userName) {
        UnitOfMeasurementEntity entity = new UnitOfMeasurementEntity();
        entity.setNombre(model.getName());
        entity.setUnidadEscalar(model.getUnitScalar());
        entity.setEstado(model.getStatus().toUpperCase().charAt(0));
        entity.setUsuarioEdita(userName);
        entity.setFechaEdita(LocalDateTime.now());
        this.repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        UnitOfMeasurementEntity entity =this.repository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Error");

        this.repository.delete(entity);
    }
}
