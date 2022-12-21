package hn.com.tigo.remision.services;


import hn.com.tigo.remision.entities.remision.LogEntity;
import hn.com.tigo.remision.models.LogInsertModel;
import hn.com.tigo.remision.models.LogModel;
import hn.com.tigo.remision.repositories.remision.LogRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LogServiceImplTest {


    @InjectMocks
    private LogServiceImpl logService;
    @Mock
    LogRepositoryImpl logRepository;

    private LogEntity entity;

    @BeforeEach
    public void setUp(){
        entity = new LogEntity("testUser",LocalDateTime.now(),"","","","","127.0.0.1");
    }
    @Test
    void getLog() {
        List<LogEntity> returnData = Arrays.asList(entity);
        given(logRepository.getAllByRange(Optional.of(LocalDate.now()).get(), Optional.of(LocalDate.now()).get())).willReturn(returnData);

        List<LogModel> data = logService.getLog(Optional.of(LocalDate.now()), Optional.of(LocalDate.now()));

        assertEquals(1, data.size());

    }

    @Test
    void insertLog() {
        LogInsertModel model = new LogInsertModel("userTest","","","","key");
        logService.insertLog(model,"127.0.0.1");

        verify(logRepository,times(1)).add(any(LogEntity.class));

    }

    @Test
    void testInsertLog() {

        logService.insertLog("userTest","","","","key","127.0.0.1");

        verify(logRepository,times(1)).add(any(LogEntity.class));

    }
}