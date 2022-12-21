package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import hn.com.tigo.remision.models.VehicleModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IVehicleService;
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

@WebMvcTest({VehicleController.class})
@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    private MockMvc mockMvc;

    @MockBean
    IVehicleService vehicleService;

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
        List<VehicleModel> ModelList = Arrays.asList(
                new VehicleModel(1L,"a","a","a","a","a","a",1L,1L,"","",LocalDateTime.now(),"",LocalDateTime.now(),"","A",null,null),
                new VehicleModel(2L,"a","a","a","a","a","a",2L,2L,"","",LocalDateTime.now(),"",LocalDateTime.now(),"","A",null,null)
        );

        when(vehicleService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/vehiculos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception {

        when(vehicleService.getById(1L)).thenReturn(new VehicleModel(1L,"a","a","a","a","a","a",2L,2L,"","",LocalDateTime.now(),"",LocalDateTime.now(),"","A",null,null));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/vehiculos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception {
        VehicleModel model =new VehicleModel(1L,"a","a","a","a","a","a",2L,2L,"","",LocalDateTime.now(),"",LocalDateTime.now(),"","A",null,null);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/vehiculos/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{
        VehicleModel model =new VehicleModel(1L,"a","a","a","a","a","a",2L,2L,"","",LocalDateTime.now(),"",LocalDateTime.now(),"","A",null,null);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/vehiculos/update/1")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception{

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/vehiculos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}