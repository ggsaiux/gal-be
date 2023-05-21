package com.asociatialocatari.gal.base.exception;

public class LoginException extends GalException {
    public LoginException(String message, ErrorEnum errorCode) {
        super(message, errorCode);
    }
}
