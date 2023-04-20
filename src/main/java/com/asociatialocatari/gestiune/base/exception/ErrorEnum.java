package com.asociatialocatari.gestiune.base.exception;

public enum ErrorEnum {

    INVALID_PARAMETERS(1),
    RUNTIME_ERROR(2),
    ERROR_3(3),
    NOTIFY_RUNTIME_ERROR(14);

    private int code;

    ErrorEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
