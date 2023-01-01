package com.aircjm.limon.system.exception;

public class CustomException  extends RuntimeException {

    private Integer code;
    private String msg;

    public CustomException(String message) {
        super(message);
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
