package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.MotoristEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.repositories.remision.IMotoristRepository;
import hn.com.tigo.remision.services.interfaces.IMotoristService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoristServiceImpl implements IMotoristService {

    private final IMotoristRepository motoristRepository;

    public MotoristServiceImpl(IMotoristRepository motoristRepository) {
        this.motoristRepository = motoristRepository;
    }

    @Override
    public List<MotoristModel> getAll() {
       List<MotoristEntity> entities = this.motoristRepository.findAll(Sort.by(Sort.Direction.DESC,"createdBy"));
       return entities.stream().map(MotoristEntity::entityToModel).collect(Collectors.toList());
    }

    @Override
    public MotoristModel getById(Long id) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error get,Record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void add(MotoristModel model) {
        MotoristEntity entity = new MotoristEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setName(model.getName());
        entity.setLastName(model.getLastName());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setIdTransportAgency(model.getIdTransportAgency());
        entity.setRtn(model.getRtn());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(model.getStatus());
        this.motoristRepository.save(entity);
    }

    @Override
    public void update(Long id, MotoristModel model) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Error update, Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setName(model.getName().toUpperCase());
        entity.setLastName(model.getLastName().toUpperCase());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setIdTransportAgency(model.getIdTransportAgency());
        entity.setRtn(model.getRtn());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        this.motoristRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));

        this.motoristRepository.delete(entity);
    }
}
