package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.VehicleEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.services.interfaces.IVehicleService;
import hn.com.tigo.remision.models.VehicleModel;
import hn.com.tigo.remision.repositories.remision.IVehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {


    private final IVehicleRepository vehicleRepository;

    public VehicleServiceImpl(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public List<VehicleModel> getAll() {
        List<VehicleEntity> entities = this.vehicleRepository.findAll();
       return  entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());

    }

    @Override
    public VehicleModel getById(Long id) {
        VehicleEntity entity = this.vehicleRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("%s not exit",id));
        return entity.entityToModel();
    }

    @Override
    public void add(VehicleModel model) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(-1L); //used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setBrand(model.getBrand());
        entity.setColor(model.getColor());
        entity.setLicensePlate(model.getLicensePlate());
        entity.setModel(model.getModel());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setIdTransportAgency(model.getIdAgency());
        entity.setIdVehicleType(model.getVehicleType());
        entity.setYear(model.getYear());
        entity.setStatus(model.getStatus());
        this.vehicleRepository.saveAndFlush(entity);
    }

    @Override
    public void update(Long id, VehicleModel model) {

        VehicleEntity entity = this.vehicleRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("%s not exist",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setId(model.getId());
        entity.setLicensePlate(model.getLicensePlate());
        entity.setBrand(model.getBrand());
        entity.setColor(model.getColor());
        entity.setModel(model.getModel());
        entity.setIdTransportAgency(model.getIdAgency());
        entity.setIdVehicleType(model.getVehicleType());
        entity.setYear(model.getYear());
        entity.setStatus(model.getStatus());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        this.vehicleRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        VehicleEntity entity = this.vehicleRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("%s not exist",id));

        this.vehicleRepository.delete(entity);
    }
}
