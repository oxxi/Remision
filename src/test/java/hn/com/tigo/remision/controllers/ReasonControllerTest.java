package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.LocationModel;
import hn.com.tigo.remision.models.ReasonModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IReasonService;
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

@WebMvcTest({ReasonController.class})
@ExtendWith(MockitoExtension.class)

class ReasonControllerTest {

    private MockMvc mockMvc;
    @MockBean
    ILogService logService;

    @MockBean
    IReasonService reasonService;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void getAll() throws Exception {

        List<ReasonModel> ModelList = Arrays.asList(
                new ReasonModel(1L,"","",LocalDateTime.now(),"","", LocalDateTime.now(),"","A"),
                new ReasonModel(2L,"","",LocalDateTime.now(),"","", LocalDateTime.now(),"","A")
        );


        when(reasonService.getAll()).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/motivos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getById() throws Exception {

        when(reasonService.getById(1L)).thenReturn(new ReasonModel(1L,"","",LocalDateTime.now(),"","", LocalDateTime.now(),"","A"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/motivos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    void add() throws Exception{

        ReasonModel model =  new ReasonModel(1L,"","",LocalDateTime.now(),"","", LocalDateTime.now(),"","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/motivos/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception{
        ReasonModel model =  new ReasonModel(1L,"","",LocalDateTime.now(),"","", LocalDateTime.now(),"","A");
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/motivos/update/1")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/motivos/1")
                             .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}