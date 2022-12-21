package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.TransportAgencyModel;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.ITransportAgencyService;
import hn.com.tigo.remision.services.interfaces.IUnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UnitController.class})
@ExtendWith(MockitoExtension.class)
class UnitControllerTest {


    private MockMvc mockMvc;

    @MockBean
    IUnitService unitService;
    @MockBean
    ILogService logService;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void getAll() throws Exception {
        List<UnitOfMeasurementModel> ModelList = Arrays.asList(
                new UnitOfMeasurementModel(1L,"","","",LocalDateTime.now(),"","",LocalDateTime.now(),"","A"),
                new UnitOfMeasurementModel(2L,"","","",LocalDateTime.now(),"","",LocalDateTime.now(),"","B")
        );


        when(unitService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/unidadmedida"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception {

        when(unitService.getById(1L)).thenReturn(new UnitOfMeasurementModel(1L,"","","",LocalDateTime.now(),"","",LocalDateTime.now(),"","A"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/unidadmedida/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception {
        UnitOfMeasurementModel model = new UnitOfMeasurementModel(1L,"aa","aa","aa",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/unidadmedida/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        UnitOfMeasurementModel model = new UnitOfMeasurementModel(1L,"aa","aa","aa",LocalDateTime.now(),"aaa","aaa",LocalDateTime.now(),"aaa","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/unidadmedida/update/1")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/unidadmedida/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}