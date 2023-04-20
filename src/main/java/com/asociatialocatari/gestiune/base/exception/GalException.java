package com.asociatialocatari.gestiune.base.exception;

public class GalException extends Exception{

    protected ErrorEnum errorCode;

    public GalException(String message, ErrorEnum errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorEnum errorCode) {
        this.errorCode = errorCode;
    }
}
