package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.MotoristEntity;
import hn.com.tigo.remision.entities.remision.ParameterEntity;
import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.repositories.remision.IParameterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ParameterServiceImplTest {


    @InjectMocks
    ParameterServiceImpl parameterService;

    @Mock
    IParameterRepository parameterRepository;

    private ParameterEntity entity;

    @BeforeEach
    public void setUp() {
        entity = new ParameterEntity("PARAMETER","Testing","test","user_test",LocalDateTime.now());
    }


    @Test
    void getAll() {
        List<ParameterEntity> returnData = Arrays.asList(entity);

        given(parameterRepository.findAll(Sort.by(Sort.Direction.DESC,"modifiedAt"))).willReturn(returnData);

        List<GeneralParameter> gp = parameterService.getAll();

        assertEquals(1L,gp.size());
    }

    @Test
    void getById() {
        given(parameterRepository.findByParameterName("PARAMETER")).willReturn(entity);

        GeneralParameter p=  parameterService.getById("PARAMETER");

        assertEquals("PARAMETER",p.getName());
    }

    @Test
    void add() {

        GeneralParameter model = new GeneralParameter("PARAMETER_NAME","VALUES PARAM","description","Testing","","modified");

        parameterService.add(model);

        verify(parameterRepository,times(1)).save(any(ParameterEntity.class));
    }

    @Test
    void update() {
        given(parameterRepository.findByParameterName("PARAMETER_NAME")).willReturn(entity);
        GeneralParameter model = new GeneralParameter("PARAMETER_NAME","VALUES PARAM","description","Testing","","modified");

        parameterService.update(model.getName(),model);

        verify(parameterRepository,times(1)).save(any(ParameterEntity.class));

    }
}