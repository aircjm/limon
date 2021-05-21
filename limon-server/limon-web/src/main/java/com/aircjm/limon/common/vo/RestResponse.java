package com.aircjm.limon.common.vo;

import lombok.Data;

/**
 * 通用返回Vo
 *
 * @author aircjm
 */
@Data
public class RestResponse<T> {

    private int code = 200;
    private String msg = "";
    private T data;

    private RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private RestResponse() {
    }

    public static <T> RestResponse <T> successEmpty() {
        return new RestResponse<>(200, "success", null);
    }



    public static <T> RestResponse<T> successData(T data) {
        return new RestResponse<>(200, "success", data);
    }



    public static <T> RestResponse<T> success(int code, String msg, T data) {
        return new RestResponse<>(code, msg, data);
    }


}
