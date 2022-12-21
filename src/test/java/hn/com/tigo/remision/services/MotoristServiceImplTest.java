package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LocationEntity;
import hn.com.tigo.remision.entities.remision.MotoristEntity;
import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.repositories.remision.IMotoristRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MotoristServiceImplTest {

    @InjectMocks
    MotoristServiceImpl motoristService;

    @Mock
    IMotoristRepository motoristRepository;

    private MotoristEntity entity;

    @BeforeEach
    public void setUp() {
        entity = new MotoristEntity(1L, 1L, "a", "a", "a", "a", LocalDateTime.now(), "a", LocalDateTime.now(), "a", "a", new TransportAgencyEntity(1L,"agencia name","","","","",LocalDateTime.now(),"",LocalDateTime.now(),"a",null));
    }

    @Test
    void getAll() {

        List<MotoristEntity> returnData = Arrays.asList(entity);
        given(motoristRepository.findAll(Sort.by(Sort.Direction.DESC,"createdBy"))).willReturn(returnData);

        List<MotoristModel> data = motoristService.getAll();

        assertEquals(1, data.size());

    }

    @Test
    void getById() {
        given(motoristRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        MotoristModel data = motoristService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void add() {

        MotoristModel mock = new MotoristModel(1L,"name","last name","",1L,"","","","","","","A");

        motoristService.add(mock);

        verify(motoristRepository,times(1)).save(any(MotoristEntity.class));
    }

    @Test
    void update() {
        given(motoristRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        MotoristModel mock = new MotoristModel(1L,"name","last name","",1L,"","","create","","modified by","","A");

        motoristService.update(mock.getId(),mock);

        verify(motoristRepository,times(1)).save(any(MotoristEntity.class));
    }

    @Test
    void delete() {
        given(motoristRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        motoristService.delete(1L);

        verify(motoristRepository,times(1)).delete(any(MotoristEntity.class));

    }
}