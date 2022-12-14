package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LocationEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.repositories.remision.ILocationRepository;
import hn.com.tigo.remision.services.interfaces.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        model.setFullAddress(entity.getAddress());
        model.setId(entity.getId());
        model.setShortCode(String.valueOf(entity.getShortName()));
        return model;
    }

    @Override
    public void add(LocationModel model) {
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
        if(entity == null) {
            //TODO:cambiar por custom exception
            throw new BadRequestException("No valid");
        }
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        // entity.setStatus(model.getStatus());
        entity.setAddress(model.getFullAddress());
        entity.setShortName(Long.parseLong(model.getShortCode()));

        this.locationRepository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        LocationEntity entity = this.locationRepository.findById(id).orElse(null);
        if(entity == null) {
            //TODO:cambiar por custom exception
            throw new RuntimeException("No valid");
        }
        this.locationRepository.delete(entity);
    }
}
