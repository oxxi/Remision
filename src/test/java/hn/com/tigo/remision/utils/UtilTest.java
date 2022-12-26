package hn.com.tigo.remision.utils;

import hn.com.tigo.remision.models.GeneralResponse;
import hn.com.tigo.remision.models.LocationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @InjectMocks
    Util util;

    @BeforeEach
    public void setUp(){
        util = new Util();
    }
    @Test
    void setSuccessResponse() {
        LocationModel  model = new LocationModel(1L,0,"","","","","","A");
        GeneralResponse gr = util.setSuccessResponse(model);

        assertEquals("Success",gr.getDescription());

    }

    @Test
    void setSuccessWithoutData() {

        GeneralResponse gr = util.setSuccessWithoutData();

        assertEquals("Success",gr.getDescription());
    }

    @Test
    void setError() {
        Exception e = new RuntimeException("error test");

        GeneralResponse gr = util.setError(e,"test");


        assertEquals(1L, gr.getErrors().size());
    }
}