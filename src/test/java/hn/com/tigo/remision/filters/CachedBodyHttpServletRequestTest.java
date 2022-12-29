package hn.com.tigo.remision.filters;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CachedBodyHttpServletRequestTest {

    @Test
    void getInputStream() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        CachedBodyHttpServletRequest body = new CachedBodyHttpServletRequest(req);

        ServletInputStream input = body.getInputStream();

        assertEquals(-1, input.read());

    }

    @Test
    void getReader() throws IOException {
        MockHttpServletRequest req = new MockHttpServletRequest();
        CachedBodyHttpServletRequest body = new CachedBodyHttpServletRequest(req);
        BufferedReader buff = body.getReader();
        assertEquals(-1, buff.read());
    }
}