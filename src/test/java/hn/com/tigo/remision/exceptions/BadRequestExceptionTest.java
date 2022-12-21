package hn.com.tigo.remision.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    void getMessage() {
        BadRequestException bad = new BadRequestException("message","internal","more info");

        assertEquals("message",bad.getMessage());
    }

    @Test
    void getInternalMessage() {
        BadRequestException bad = new BadRequestException("message","internal","more info");

        assertEquals("internal",bad.getInternalMessage());
    }

    @Test
    void getMoreInfo() {
        BadRequestException bad = new BadRequestException("message","internal","more info");

        assertEquals("more info",bad.getMoreInfo());
    }
}