package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.LocationEntity;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.repositories.remision.ILocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;
    @Mock
    ILocationRepository locationRepository;

    private LocationEntity entity;

    @BeforeEach
    public void setUp(){
        entity = new LocationEntity(1L,"aaa",123L,"", LocalDateTime.now(),"",LocalDateTime.now(),"A");
    }

    @Test
    void getAll() {
        List<LocationEntity> returnData = Arrays.asList(entity);
        given(locationRepository.findAll()).willReturn(returnData);

        List<LocationModel> data = locationService.getAll();

        assertEquals(1, data.size());
    }

    @Test
    void getById() {

        given(locationRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        LocationModel model = locationService.getById(1L);

        assertEquals(1L,model.getId());


    }

    @Test
    void add() {

        LocationModel model = new LocationModel(2L,"123","address","test","","","","A");

        locationService.add(model);

        verify(locationRepository,times(1)).save(any(LocationEntity.class));

    }

    @Test
    void update() {

        given(locationRepository.findById(2L)).willReturn(Optional.ofNullable(entity));
        LocationModel model = new LocationModel(2L,"123","address","test","","","","A");

        locationService.update(2L,model);

        verify(locationRepository,times(1)).saveAndFlush(any(LocationEntity.class));

    }

    @Test
    void delete() {
        given(locationRepository.findById(2L)).willReturn(Optional.ofNullable(entity));
        locationService.delete(2L);

        verify(locationRepository,times(1)).delete(any(LocationEntity.class));
    }
}