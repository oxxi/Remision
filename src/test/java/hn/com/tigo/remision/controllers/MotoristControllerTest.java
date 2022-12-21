package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.services.interfaces.ILocationService;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IMotoristService;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({MotoristController.class})
@ExtendWith(MockitoExtension.class)
class MotoristControllerTest {

    private MockMvc mockMvc;
    @MockBean
    IMotoristService motoristService;

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

        List<MotoristModel> ModelList = Arrays.asList(
                new MotoristModel(1L,"name","last name","",2L,",","","","","","","A"),
                new MotoristModel(1L,"name","last name","",2L,",","","","","","","B")
        );


        when(motoristService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/conductores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception {
        when(motoristService.getById(1L)).thenReturn(new MotoristModel(1L,"name","last name","",2L,",","","","","","","A"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/conductores/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception {
        MotoristModel model =  new MotoristModel(1L,"name","last name","",2L,",","","","","","","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/conductores/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        MotoristModel model =  new MotoristModel(1L,"name","last name","",2L,",","","","","","","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/conductores/update/1")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/conductores/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}