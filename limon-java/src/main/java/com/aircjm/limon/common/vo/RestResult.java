package com.aircjm.limon.common.vo;

import lombok.Data;

/**
 * 通用返回Vo
 *
 * @author aircjm
 */
@Data
public class RestResult<T> {

    private int code = 200;
    private String msg = "";
    private T data;

    private RestResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private RestResult() {
    }

    public static <T> RestResult<T> successEmpty() {
        return new RestResult<>(200, "success", null);
    }



    public static <T> RestResult<T> successData(T data) {
        return new RestResult<>(200, "success", data);
    }



    public static <T> RestResult<T> success(int code, String msg, T data) {
        return new RestResult<>(code, msg, data);
    }


}
