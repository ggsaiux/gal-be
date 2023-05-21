package com.asociatialocatari.gal.base.exception;

public class UserException extends GalException {

    public UserException(String message) {
        super(message, ErrorEnum.RUNTIME_ERROR);
    }

    public UserException(String message, ErrorEnum errorCode) {
        super(message, errorCode);
    }
}
