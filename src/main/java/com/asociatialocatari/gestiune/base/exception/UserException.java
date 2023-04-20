package com.asociatialocatari.gestiune.base.exception;

import com.asociatialocatari.gestiune.base.exception.GalException;
import com.asociatialocatari.gestiune.base.exception.ErrorEnum;

public class UserException extends GalException {

    public UserException(String message) {
        super(message, ErrorEnum.RUNTIME_ERROR);
    }

    public UserException(String message, ErrorEnum errorCode) {
        super(message, errorCode);
    }
}
