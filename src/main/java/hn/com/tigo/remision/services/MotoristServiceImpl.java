package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.MotoristEntity;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.repositories.remision.IMotoristRepository;
import hn.com.tigo.remision.services.interfaces.IMotoristService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
       return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public MotoristModel getById(Long id) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");
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
        entity.setCreatedAt(LocalDate.now());
        this.motoristRepository.save(entity);
    }

    @Override
    public void update(Long id, MotoristModel model) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");
        entity.setName(model.getName());
        entity.setLastName(model.getLastName());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setIdTransportAgency(model.getIdTransportAgency());
        entity.setRtn(model.getRtn());
        entity.setModifiedAt(LocalDate.now());
        entity.setModifiedBy(model.getModifiedBy());
    }

    @Override
    public void delete(long id) {
        MotoristEntity entity = this.motoristRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");

        this.motoristRepository.delete(entity);
    }
}
