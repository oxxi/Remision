package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.models.TransportAgencyModel;
import hn.com.tigo.remision.repositories.remision.ITransportAgencyRepository;
import hn.com.tigo.remision.services.interfaces.ITransportAgencyService;
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

       List<TransportAgencyEntity> entities = this.transportAgencyRepository.findAll();
       return entities.stream().map(e-> e.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public TransportAgencyModel getById(Long id) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");
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
        this.transportAgencyRepository.save(entity);

    }

    @Override
    public void update(Long id,TransportAgencyModel model) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");
        entity.setName(model.getName());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifiedBy(model.getModifiedBy());
        entity.setAddress(model.getAddress());
        entity.setRtn(model.getRtn());
        entity.setPhoneNumber(model.getPhoneNumber());

        this.transportAgencyRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        TransportAgencyEntity entity = this.transportAgencyRepository.findById(id).orElse(null);
        if(entity == null) throw new RuntimeException("Cambiar por respuesta de Tigo");

        this.transportAgencyRepository.delete(entity);
    }
}
