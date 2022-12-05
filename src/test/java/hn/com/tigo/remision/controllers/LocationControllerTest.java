package hn.com.tigo.remision.controllers;

import hn.com.tigo.remision.models.LocationModel;

import hn.com.tigo.remision.services.LocationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class LocationControllerTest {


    private MockMvc mockMvc;
    @MockBean
    LocationServiceImpl locationService;




    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAll_When_isOk() throws Exception {
        List<LocationModel> locationModelList = Arrays.asList(
            new LocationModel(1L,"ShortCode1","full address","","","","",""),
            new LocationModel(2L,"ShortCode2","full address 2","","","","","")
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
    void getById_IsOK() throws Exception{
        when(locationService.getById(1L)).thenReturn( new LocationModel(1L,"ShortCode1","full address","","","","",""));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/ubicacion/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }
//
//    @Test
//    void add() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
}