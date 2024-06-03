package com.reading.handler;

import com.reading.config.dto.ErrorResponse;
import com.reading.exception.BadRequestException;
import com.reading.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOGGING_MESSAGE = "예외 발생 : ";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequestException(final BadRequestException e) {
        log.error("Bad request..", ERROR_LOGGING_MESSAGE, e);
        return new ErrorResponse(e.getErrorCode(), e.getClientMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(final NotFoundException e) {
        log.error("Not found..",ERROR_LOGGING_MESSAGE, e);
        return new ErrorResponse(e.getErrorCode(), e.getClientMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(final Exception e) {
        if(e instanceof NotFoundException) {
            throw (NotFoundException) e;
        }
        if(e instanceof NoResourceFoundException) {
            throw (NotFoundException) e;
        }
        log.error("Unexpected error.. occurred", "예상하지 못한 에러가 발생하였습니다.", e);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
    }

}
