package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.TransportAgencyModel;
import hn.com.tigo.remision.repositories.remision.ITransportAgencyRepository;
import hn.com.tigo.remision.services.interfaces.ITransportAgencyService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportAgencyServiceImpl implements ITransportAgencyService {

    private final ITransportAgencyRepository transportAgencyRepository;

    public TransportAgencyServiceImpl(ITransportAgencyRepository transportAgencyRepository) {
        this.transportAgencyRepository = transportAgencyRepository;
    }


    @Override
    public List<TransportAgencyModel> getAll() {

       List<TransportAgencyEntity> entities = this.transportAgencyRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
       return entities.stream().map(TransportAgencyEntity::entityToModel).collect(Collectors.toList());
    }

    @Override
    public TransportAgencyModel getById(Long id) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error get record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void add(TransportAgencyModel model) {
        TransportAgencyEntity entity = new TransportAgencyEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setName(model.getName());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setAddress(model.getAddress());
        entity.setRtn(model.getRtn());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setStatus(model.getStatus());
        this.transportAgencyRepository.save(entity);

    }

    @Override
    public void update(Long id,TransportAgencyModel model) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error update, record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setName(model.getName());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setAddress(model.getAddress());
        entity.setRtn(model.getRtn());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setStatus(model.getStatus());
        this.transportAgencyRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));

        this.transportAgencyRepository.delete(entity);
    }
}
