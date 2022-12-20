package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.VehicleTypeEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
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
        return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public VehicleTypesModel getById(Long id) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        return entity.entityToModel();

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
    public void update(Long id, VehicleTypesModel model) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setModifiedBy(model.getModifiedBy());
        entity.setCreateBy(model.getCreatedBy());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setDescription(model.getDescription());

        this.vehicleTypeRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        VehicleTypeEntity entity = this.vehicleTypeRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));

        this.vehicleTypeRepository.delete(entity);
    }
}
