package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.GeneralParameter;
import hn.com.tigo.remision.models.MotoristModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IParameterService;
import org.junit.jupiter.api.Assertions;
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

@WebMvcTest({ParameterController.class})
@ExtendWith(MockitoExtension.class)
public class ParameterControllerTest {

    private MockMvc mockMvc;

    @MockBean
    IParameterService parameterService;
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
        List<GeneralParameter> ModelList = Arrays.asList(
                new GeneralParameter("parameter","name","last name","","",""),
                new GeneralParameter("parameter2","name","last name","","","")
        );

        when(parameterService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/parametros"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getByName() throws Exception {

        when(parameterService.getById("name_parameter")).thenReturn( new GeneralParameter("1111","name","last name","","",""));

        mockMvc.perform(
                 MockMvcRequestBuilders.get("/parametros/name/name_parameter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));

    }

    @Test
    void add() throws Exception{
        GeneralParameter model= new GeneralParameter("parameter2","name","last name","","","");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/parametros/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {

        GeneralParameter model= new GeneralParameter("parameter2","name","last name","","","");

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/parametros/update/parameter2")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


}