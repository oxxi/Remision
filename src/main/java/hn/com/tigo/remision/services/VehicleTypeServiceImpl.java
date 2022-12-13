package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.VehicleTypeEntity;
import hn.com.tigo.remision.services.interfaces.IVehicleTypeService;
import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.repositories.remision.IVehicleTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleTypeServiceImpl implements IVehicleTypeService {
    private final IVehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeServiceImpl(IVehicleTypeRepository typeOfTransportRepository) {
        this.vehicleTypeRepository = typeOfTransportRepository;
    }

    @Override
    public List<VehicleTypesModel> getAll() {
        List<VehicleTypeEntity> entities = this.vehicleTypeRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        return entities.stream().map(e->{
            VehicleTypesModel model = new VehicleTypesModel();
            model.setId(e.getId());
            model.setDescription(e.getDescription());
            model.setStatus(String.valueOf(e.getStatus()));
            model.setCreatedBy(e.getCreateBy());
            model.setModifiedBy(e.getModifiedBy());
            model.setModifiedAt(e.getModifiedAt());
            model.setCreatedAt(e.getCreatedAt());
            return model;
        }).collect(Collectors.toList());
    }

    @Override
    public VehicleTypesModel getById(Long id) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Error cambiar por standard");
        VehicleTypesModel model = new VehicleTypesModel();
        model.setId(entity.getId());
        model.setDescription(entity.getDescription());
        model.setStatus(String.valueOf(entity.getStatus()));
        model.setCreatedBy(entity.getCreateBy());
        model.setModifiedBy(entity.getModifiedBy());
        model.setModifiedAt(entity.getModifiedAt());
        model.setCreatedAt(entity.getCreatedAt());
        return model;
    }

    @Override
    public void add(VehicleTypesModel model) {
        VehicleTypeEntity entity = new VehicleTypeEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setDescription(model.getDescription());
        entity.setCreateBy(model.getCreatedBy());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus("A");
        this.vehicleTypeRepository.save(entity);
    }

    @Override
    public void update(Long id, VehicleTypesModel model, String userName) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("cambiar por standard tigo");

        entity.setModifiedBy(model.getModifiedBy());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setDescription(model.getDescription());

        this.vehicleTypeRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("cambiar por standard tigo");

        this.vehicleTypeRepository.delete(entity);
    }
}
