package hn.com.tigo.remision.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hn.com.tigo.remision.models.AuthModel;
import hn.com.tigo.remision.models.LogModel;
import hn.com.tigo.remision.services.interfaces.ILogService;
import hn.com.tigo.remision.services.interfaces.IMotoristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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


@WebMvcTest({LogController.class})
@ExtendWith(MockitoExtension.class)
class LogControllerTest {

    private MockMvc mockMvc;
    @MockBean
    ILogService logService;


    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    void getData() throws Exception {
        List<LogModel> ModelList =  Arrays.asList(
            new LogModel("a","b","c","d","","","",""),
             new LogModel("a1","b2","c3","d4","5","6","7","127.0.0.1")

        );
        when(logService.getLog(null,null)).thenReturn(ModelList);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/log"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{}")
                ).andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void insert() throws Exception{
        AuthModel authModel = new AuthModel();
        authModel.setUserName("testing");
        authModel.setIp("127.0.0.1");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authModel,authModel);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);


        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        LogModel model =  new LogModel("a","b","c","d","","","","0.0.0.0");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/log/add")
                                .content(objectMapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}