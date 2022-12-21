package hn.com.tigo.remision.services;

import hn.com.tigo.remision.entities.remision.ReasonEntity;
import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.repositories.remision.IReasonRepository;
import org.junit.Assert;
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
public class ReasonServiceTest {

    @InjectMocks
    ReasonService reasonService;
    @Mock
    IReasonRepository reasonRepository;

    private ReasonEntity entity;

    @BeforeEach
    public void setUp(){
        entity = new ReasonEntity(1L,"","",LocalDateTime.now(),"", LocalDateTime.now(),"A");
    }

    @Test
    void getAll() {
        List<ReasonEntity> returnData = Arrays.asList(entity);

        given(reasonRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt","id"))).willReturn(returnData);

        List<ReasonModel> data = reasonService.getAll();

        assertEquals(1L,data.size());

    }

    @Test
    void getById() {

        given(reasonRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        ReasonModel data = reasonService.getById(1L);

        assertEquals(1L,data.getId());

    }

    @Test
    void add() {
        ReasonModel model = new ReasonModel(1L,"description","test",LocalDateTime.now(),"","test",LocalDateTime.now(),"","A");
        reasonService.add(model);

        verify(reasonRepository,times(1)).save(any(ReasonEntity.class));
    }

    @Test
    void update() {
        ReasonModel model = new ReasonModel(1L,"description","test",LocalDateTime.now(),"","test",LocalDateTime.now(),"","A");

        given(reasonRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        reasonService.update(model.getId(),model);

        verify(reasonRepository,times(1)).save(any(ReasonEntity.class));

    }

    @Test
    void delete() {

        given(reasonRepository.findById(1L)).willReturn(Optional.ofNullable(entity));

        reasonService.delete(1L);

        verify(reasonRepository,times(1)).delete(any(ReasonEntity.class));
    }
}