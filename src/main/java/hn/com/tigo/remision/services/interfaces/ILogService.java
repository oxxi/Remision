package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.entities.remision.LogEntity;
import hn.com.tigo.remision.models.LogInsertModel;
import hn.com.tigo.remision.models.LogModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILogService {

    List<LogModel> getLog(Optional<LocalDate> ini, Optional<LocalDate> end);

    void insertLog(LogInsertModel entity, String ip);

    void insertLog(String user,String module,String action, String object,String key,String ip);
}
