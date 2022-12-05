package hn.com.tigo.remision.services.interfaces;

import hn.com.tigo.remision.models.LogModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILogService {

    List<LogModel> getLog(Optional<LocalDate> ini, Optional<LocalDate> end);
}
