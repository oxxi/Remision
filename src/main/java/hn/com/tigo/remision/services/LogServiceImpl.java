package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LogEntity;
import hn.com.tigo.remision.models.LogInsertModel;
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
          return entities.stream().map(LogEntity::entityToModel).collect(Collectors.toList());
        }
        entities = this.logRepository.getAll();
        return entities.stream().map(LogEntity::entityToModel).collect(Collectors.toList());
    }

    @Override
    public void insertLog(LogInsertModel model, String ip) {

        LogEntity entity = new LogEntity();
        entity.setUserName(model.getUser());
        entity.setModule(model.getModule());
        entity.setAction(model.getAction());
        entity.setObject(model.getObject());
        entity.setKey(model.getKey());
        entity.setIp(ip);
        this.logRepository.add(entity);
    }

    @Override
    public void insertLog(String user, String module, String action, String object, String key, String ip) {
        LogEntity entity = new LogEntity();
        entity.setUserName(user);
        entity.setModule(module);
        entity.setAction(module);
        entity.setObject(object);
        entity.setKey(key);
        entity.setIp(ip);
        this.logRepository.add(entity);
    }
}
