package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.VehicleTypesModel;
import hn.com.tigo.remision.models.WarehouseManagerModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IVehicleTypeService;
import hn.com.tigo.remision.services.interfaces.IWarehouseManagerService;
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

@WebMvcTest({WarehouseManagerController.class})
@ExtendWith(MockitoExtension.class)
public class WarehouseManagerControllerTest {


    private MockMvc mockMvc;
    @MockBean
    IWarehouseManagerService warehouseManagerService;

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

        List<WarehouseManagerModel> ModelList = Arrays.asList(
                new WarehouseManagerModel(1L,"name","last","dn1","create",LocalDateTime.now(),"","",LocalDateTime.now(),"","A"),
                new WarehouseManagerModel(2L,"name","last","dn1","create",LocalDateTime.now(),"","",LocalDateTime.now(),"","B")
        );
        when(warehouseManagerService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/encargadobodega"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception{

        when(warehouseManagerService.getById(1L)).thenReturn(new WarehouseManagerModel(1L,"name","last","dn1","create",LocalDateTime.now(),"","",LocalDateTime.now(),"","A"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/encargadobodega/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception{
        WarehouseManagerModel model  = new WarehouseManagerModel(1L,"name","last","dn1","create",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/encargadobodega/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{
        WarehouseManagerModel model  = new WarehouseManagerModel(1L,"name","last","dn1","create",LocalDateTime.now(),"","",LocalDateTime.now(),"","A");

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/encargadobodega/update/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(model))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/encargadobodega/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}