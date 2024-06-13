package com.reading.exception;

public class NoResourceFoundException extends RuntimeException {

    private String errorCode = "No Resources";
    private String clientMessage = "자원을 찾을 수 없습니다...";

    public NoResourceFoundException(final String serverMessage, final String clientMessage, final String errorCode) {
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
