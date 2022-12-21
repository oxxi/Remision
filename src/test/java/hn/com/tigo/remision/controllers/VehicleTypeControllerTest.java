package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IVehicleTypeService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({VehicleTypeController.class})
@ExtendWith(MockitoExtension.class)
public class VehicleTypeControllerTest {

    private MockMvc mockMvc;
    @MockBean
    IVehicleTypeService vehicleTypeService;

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

        List<VehicleTypesModel> ModelList = Arrays.asList(
                new VehicleTypesModel(1L,"","", LocalDateTime.now(),"","",LocalDateTime.now(),"","A"),
                new VehicleTypesModel(2L,"","", LocalDateTime.now(),"","",LocalDateTime.now(),"","A")
        );
        when(vehicleTypeService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/tipovehiculos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception{

        when(vehicleTypeService.getById(1L)).thenReturn(new VehicleTypesModel(1L,"","", LocalDateTime.now(),"","",LocalDateTime.now(),"","A"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/tipovehiculos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception{
        VehicleTypesModel model = new VehicleTypesModel(1L,"a","a", LocalDateTime.now(),"a","a",LocalDateTime.now(),"a","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/tipovehiculos/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{

        VehicleTypesModel model = new VehicleTypesModel(1L,"test description","test", LocalDateTime.now(),"aaaa","aaaaa",LocalDateTime.now(),"aaa","A");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/tipovehiculos/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(model))
        ).andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/tipovehiculos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}