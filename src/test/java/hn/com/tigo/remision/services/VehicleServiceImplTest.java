package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.entities.remision.VehicleEntity;
import hn.com.tigo.remision.entities.remision.VehicleTypeEntity;
import hn.com.tigo.remision.models.VehicleModel;
import hn.com.tigo.remision.repositories.remision.IVehicleRepository;
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
public class VehicleServiceImplTest {

    @InjectMocks
    VehicleServiceImpl vehicleService;

    @Mock
    IVehicleRepository vehicleRepository;

    private VehicleEntity entity;

    @BeforeEach
    public void setUp(){
        entity = new VehicleEntity(1L,"",1L,1L,"","","","","", LocalDateTime.now(),"",LocalDateTime.now(),"A",new VehicleTypeEntity()
                ,new TransportAgencyEntity(1L,"Test agencia","","","","",LocalDateTime.now(),"",LocalDateTime.now(),"A",null));
    }
    @Test
    void getAll() {

        List<VehicleEntity> returnData = Collections.singletonList(entity);

        given(vehicleRepository.findAll()).willReturn(returnData);

        List<VehicleModel> data = vehicleService.getAll();

        assertEquals(1L,data.size());
    }

    @Test
    void getById() {
        given(vehicleRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        VehicleModel data = vehicleService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void add() {
        VehicleModel model = new VehicleModel(1L,"a","a","","","","",1L,1L,"","Test user",LocalDateTime.now(),"",LocalDateTime.now(),"","",null,null);
        vehicleService.add(model);

        verify(vehicleRepository,times(1)).saveAndFlush(any(VehicleEntity.class));
    }

    @Test
    void update() {
        VehicleModel model = new VehicleModel(1L,"a","a","","","","",1L,1L,"","Test user",LocalDateTime.now(),"",LocalDateTime.now(),"","",null,null);
        given(vehicleRepository.findById(1L)).willReturn(Optional.ofNullable(entity));
        vehicleService.update(model.getId(), model);

        verify(vehicleRepository,times(1)).saveAndFlush(any(VehicleEntity.class));
    }

    @Test
    void delete() {
        given(vehicleRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        vehicleService.delete(1L);

        verify(vehicleRepository,times(1)).delete(any(VehicleEntity.class));
    }
}