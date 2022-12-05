package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LogEntity;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.models.LogModel;
import hn.com.tigo.remision.repositories.remision.LogRepositoryImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements ILogService {

    private final LogRepositoryImpl logRepository;

    public LogServiceImpl(LogRepositoryImpl logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<LogModel> getLog(Optional<LocalDate> ini, Optional<LocalDate> end) {
        List<LogEntity> entities;
        if (ini.isPresent() && end.isPresent()) {
          entities =  this.logRepository.getAllByRange(ini.get(), end.get());
          return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
        }
        entities = this.logRepository.getAll();
        return entities.stream().map(e->e.entityToModel()).collect(Collectors.toList());
    }
}
