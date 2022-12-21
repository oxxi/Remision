package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.WarehouseManagerEntity;
import hn.com.tigo.remision.models.WarehouseManagerModel;
import hn.com.tigo.remision.repositories.remision.IWarehouseManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WarehouseManagerServiceTest {

    @InjectMocks
    WarehouseManagerService warehouseManagerService;

    @Mock
    IWarehouseManagerRepository managerRepository;

    private WarehouseManagerEntity entity;

    private WarehouseManagerModel model;

    @BeforeEach
    public void setUp(){
        entity = new WarehouseManagerEntity(1L,"name","last name","112124","Test Create",LocalDateTime.now(),"Test modified", LocalDateTime.now(),"A");
        model = new WarehouseManagerModel(2L,"name 2", "my last name","2211221","create user",LocalDateTime.now(),"","User modified",LocalDateTime.now(),"","B");
    }
    @Test
    void getAll() {

        List<WarehouseManagerEntity> returnData = Collections.singletonList(entity);

        given(managerRepository.findAll()).willReturn(returnData);

        List<WarehouseManagerModel> data = warehouseManagerService.getAll();

        assertEquals(1L,data.size());
    }

    @Test
    void getById() {

        given(managerRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        WarehouseManagerModel data = warehouseManagerService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void add() {

        warehouseManagerService.add(model);

        verify(managerRepository,times(1)).save(any(WarehouseManagerEntity.class));
    }

    @Test
    void update() {
        given(managerRepository.findById(2L)).willReturn(Optional.ofNullable(entity));
        warehouseManagerService.update(model.getId(), model);

        verify(managerRepository,times(1)).save(any(WarehouseManagerEntity.class));
    }

    @Test
    void delete() {

        given(managerRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        warehouseManagerService.delete(1L);

        verify(managerRepository,times(1)).delete(any(WarehouseManagerEntity.class));
    }
}