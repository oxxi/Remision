package hn.com.tigo.remision.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.LocationModel;

import hn.com.tigo.remision.services.interfaces.ILocationService;
import hn.com.tigo.remision.services.interfaces.ILogService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({LocationController.class})
@ExtendWith(MockitoExtension.class)
public class LocationControllerTest {

    private MockMvc mockMvc;
    @MockBean
    ILocationService locationService;

    @MockBean
    ILogService logService;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAll_When_isOk() throws Exception {

    }

    @Test
    void getAll_when_is_empty() throws Exception {

        when(locationService.getAll()).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/ubicacion"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void getAll() throws Exception {
        List<LocationModel> locationModelList = Arrays.asList(
                new LocationModel(1L,1,"full address","","","","",""),
                new LocationModel(2L,1,"full address 2","","","","","")
        );


        when(locationService.getAll()).thenReturn(locationModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/ubicacion"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception {
        when(locationService.getById(1L)).thenReturn( new LocationModel(1L,1,"full address","","","","",""));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/ubicacion/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception {
        LocationModel model =   new LocationModel(1L,1,"full address","","","","","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/ubicacion/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                    )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        LocationModel model =   new LocationModel(1L,1,"full address","","","Test","","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/ubicacion/update/1")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/ubicacion/1")
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

}