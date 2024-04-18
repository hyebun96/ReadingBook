package com.reading.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NotFoundException extends RuntimeException{

    private String errorCode = "NOT_FOUND";
    private String clientMessage = "해당 정보를 조회하지 못했습니다.";

    public NotFoundException(final String serverMessage, final String clientMessage, final String errorCode) {
        super(serverMessage);
        this.clientMessage = clientMessage;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getClientMessage() {
        return clientMessage;
    }
}
