package hn.com.tigo.remision.services;


import hn.com.tigo.remision.entities.remision.VehicleTypeEntity;

import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.repositories.remision.IVehicleTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

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
public class VehicleTypeServiceImplTest {

    @InjectMocks
    VehicleTypeServiceImpl vehicleTypeService;

    @Mock
    IVehicleTypeRepository vehicleTypeRepository;

    private VehicleTypeEntity entity;

    @BeforeEach
    public void setUp(){
        entity = new VehicleTypeEntity(1L,"description","test Crea",LocalDateTime.now(),"test update",LocalDateTime.now(),"A",null);
    }

    @Test
    void getAll() {
        List<VehicleTypeEntity> returnData = Collections.singletonList(entity);

        given(vehicleTypeRepository.findAll(Sort.by(Sort.Direction.ASC,"id"))).willReturn(returnData);

        List<VehicleTypesModel> data = vehicleTypeService.getAll();

        assertEquals(1L,data.size());

    }

    @Test
    void getById() {
        given(vehicleTypeRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        VehicleTypesModel data = vehicleTypeService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void add() {
        VehicleTypesModel model = new VehicleTypesModel(1L,"name","Test",LocalDateTime.now(),"","",LocalDateTime.now(),"Test","A");

        vehicleTypeService.add(model);

        verify(vehicleTypeRepository,times(1)).save(any(VehicleTypeEntity.class));
    }

    @Test
    void update() {
        VehicleTypesModel model = new VehicleTypesModel(1L,"name","Test",LocalDateTime.now(),"","",LocalDateTime.now(),"Test","A");
        given(vehicleTypeRepository.findById(1L)).willReturn(Optional.ofNullable(entity));
        vehicleTypeService.update(model.getId(), model);

        verify(vehicleTypeRepository,times(1)).saveAndFlush(any(VehicleTypeEntity.class));
    }

    @Test
    void delete() {

        given(vehicleTypeRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        vehicleTypeService.delete(1L);

        verify(vehicleTypeRepository,times(1)).delete(any(VehicleTypeEntity.class));
    }
}