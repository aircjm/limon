package com.aircjm.limon.system.vo;

import lombok.Data;

/**
 * 通用返回Vo
 *
 * @author aircjm
 */
@Data
public class Result<T> {

    private int code = 0;
    private String msg = "";
    private T data;

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Result<T> fail(String msg) {
        return new Result<>(9999, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }


    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }


}
