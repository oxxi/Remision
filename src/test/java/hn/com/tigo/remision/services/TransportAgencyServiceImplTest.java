package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.ReasonEntity;
import hn.com.tigo.remision.entities.remision.TransportAgencyEntity;
import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.models.TransportAgencyModel;
import hn.com.tigo.remision.repositories.remision.ITransportAgencyRepository;
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
public class TransportAgencyServiceImplTest {

    @InjectMocks
    TransportAgencyServiceImpl transportAgencyService;

    @Mock
    ITransportAgencyRepository transportAgencyRepository;

    private TransportAgencyEntity entity;
    @BeforeEach
    public void setUp(){
        entity = new TransportAgencyEntity(1L,"","","","","",LocalDateTime.now(),"", LocalDateTime.now(),"A",null);
    }

    @Test
    void getAll() {
        List<TransportAgencyEntity> returnData = Arrays.asList(entity);

        given(transportAgencyRepository.findAll(Sort.by(Sort.Direction.DESC,"id"))).willReturn(returnData);

        List<TransportAgencyModel> data = transportAgencyService.getAll();

        assertEquals(1L,data.size());
    }

    @Test
    void getById() {
        given(transportAgencyRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        TransportAgencyModel data = transportAgencyService.getById(1L);

        assertEquals(1L,data.getId());
    }

    @Test
    void add() {
        TransportAgencyModel model = new TransportAgencyModel(1L,"name","","","","",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");
        transportAgencyService.add(model);

        verify(transportAgencyRepository,times(1)).save(any(TransportAgencyEntity.class));
    }

    @Test
    void update() {
        TransportAgencyModel model = new TransportAgencyModel(1L,"name","","","","",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");

        given(transportAgencyRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        transportAgencyService.update(model.getId(),model);

        verify(transportAgencyRepository,times(1)).save(any(TransportAgencyEntity.class));
    }

    @Test
    void delete() {
        given(transportAgencyRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        transportAgencyService.delete(1L);

        verify(transportAgencyRepository,times(1)).delete(any(TransportAgencyEntity.class));
    }
}