package com.aircjm.common.vo;

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

    public static RestResponse success() {
        return new RestResponse<>(200, "success", null);
    }


    public static RestResponse success(int code, String msg, Object data) {
        return new RestResponse<>(code, msg, data);
    }


}
