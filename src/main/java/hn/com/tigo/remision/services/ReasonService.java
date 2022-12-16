package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.ReasonEntity;
import hn.com.tigo.remision.exceptions.BadRequestException;
import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.repositories.remision.IReasonRepository;
import hn.com.tigo.remision.services.interfaces.IReasonService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReasonService implements IReasonService {

    private final IReasonRepository reasonRepository;

    public ReasonService(IReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }


    @Override
    public List<ReasonModel> getAll() {
        List<ReasonEntity> entities = this.reasonRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt","id"));
        return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
    }

    @Override
    public ReasonModel getById(Long id) {
        ReasonEntity entity= this.reasonRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        return entity.entityToModel();
    }

    @Override
    public void add(ReasonModel model) {
        ReasonEntity entity = new ReasonEntity();
        entity.setId(-1L);//used this to avoid error of identity generation in Hibernate it's no the best way, but works in this scenario when the db already exist
        entity.setDescription(model.getDescription());
        entity.setStatus(model.getStatus());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedAt(LocalDate.now());

        this.reasonRepository.save(entity);
    }

    @Override
    public void update(Long id, ReasonModel model) {
        ReasonEntity entity= this.reasonRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        if(model.getModifiedBy() == null) throw new BadRequestException("Field Modified by is required");

        entity.setDescription(model.getDescription());
        entity.setStatus(model.getStatus());
        entity.setModifiedAt(LocalDate.now());
        entity.setModifiedBy(model.getModifiedBy());
        this.reasonRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        ReasonEntity entity= this.reasonRepository.findById(id).orElse(null);
        if(entity == null) throw new BadRequestException(String.format("Record with id %s is not valid",id));
        this.reasonRepository.delete(entity);
    }
}
