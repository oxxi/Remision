package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LocationEntity;

import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.repositories.remision.ILocationRepository;
import hn.com.tigo.remision.services.interfaces.ILocationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements ILocationService {


    private final ILocationRepository locationRepository;




    public LocationServiceImpl(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;

    }


    @Override
    public List<LocationModel> getAll() {

       List<LocationEntity> entities = this.locationRepository.findAll();
       return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public LocationModel getById(Long id) {
        LocationModel model = new LocationModel();
        LocationEntity entity = this.locationRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void add(LocationModel model) {

        LocationEntity existEntity=this.locationRepository.findByshortName(model.getShortCode());
        if(existEntity != null) throw new BadRequestException(String.format("shortCode %s already exist",model.getShortCode()));

        LocationEntity entity = new LocationEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setAddress(model.getFullAddress());
        entity.setShortName(Long.parseLong(model.getShortCode()));
        entity.setUserCreated(model.getCreatedBy());
        entity.setStatus(model.getStatus());
        entity.setCreatedAt(LocalDateTime.now());
        this.locationRepository.save(entity);

    }

    @Override
    public void update(Long id,LocationModel model) {
        LocationEntity entity = this.locationRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setStatus(model.getStatus() == null ? entity.getStatus(): model.getStatus());
        entity.setAddress(model.getFullAddress());
        entity.setShortName(Long.parseLong(model.getShortCode()));
        this.locationRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        LocationEntity entity = this.locationRepository.findById(id).orElse(null);
        if(entity == null) {
            throw new BadRequestException(String.format("Record with id %s is not valid",id));
        }
        this.locationRepository.delete(entity);
    }

}
