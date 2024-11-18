package com.reading.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BadRequestExceptionTest {

    private BadRequestException badRequestException;

    @Test
    public void badReqeusTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new BadRequestException("server message", "client message", "error message");
        });
    }

}