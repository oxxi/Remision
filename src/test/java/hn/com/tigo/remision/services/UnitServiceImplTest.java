package hn.com.tigo.remision.services;


import hn.com.tigo.remision.entities.remision.UnitOfMeasurementEntity;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.repositories.remision.IUnitOfMeasurementRepository;
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
public class UnitServiceImplTest {

    @InjectMocks
    UnitServiceImpl unitService;

    @Mock
    IUnitOfMeasurementRepository repository;

    private UnitOfMeasurementEntity entity;

    @BeforeEach
    public void setUp(){

        entity = new UnitOfMeasurementEntity(1L,"Name","M","User Test",LocalDateTime.now(),"",LocalDateTime.now(),"A");
    }



    @Test
    void getAll() {
        List<UnitOfMeasurementEntity> returnData = Arrays.asList(entity);

        given(repository.findAll(Sort.by(Sort.Direction.DESC,"id"))).willReturn(returnData);

        List<UnitOfMeasurementModel> data = unitService.getAll();

        assertEquals(1L,data.size());
    }

    @Test
    void getById() {
        given(repository.findById(1L)).willReturn(Optional.ofNullable(entity));

        UnitOfMeasurementModel data = unitService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void update() {
        UnitOfMeasurementModel model = new UnitOfMeasurementModel(1L,"name test","M","userName",LocalDateTime.now(),"","User Test",LocalDateTime.now(),"","A");
        given(repository.findById(1L)).willReturn(Optional.ofNullable(entity));
        unitService.update(model.getId(), model);

        verify(repository,times(1)).saveAndFlush(any(UnitOfMeasurementEntity.class));
    }

    @Test
    void add() {
        UnitOfMeasurementModel model = new UnitOfMeasurementModel(1L,"name","s","a",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");
        unitService.add(model);

        verify(repository,times(1)).save(any(UnitOfMeasurementEntity.class));
    }

    @Test
    void delete() {

        given(repository.findById(1L)).willReturn(Optional.ofNullable(entity));

        unitService.delete(1L);

        verify(repository,times(1)).delete(any(UnitOfMeasurementEntity.class));
    }
}