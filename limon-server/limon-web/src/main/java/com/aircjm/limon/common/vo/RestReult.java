package com.aircjm.limon.common.vo;

import lombok.Data;

/**
 * 通用返回Vo
 *
 * @author aircjm
 */
@Data
public class RestReult<T> {

    private int code = 200;
    private String msg = "";
    private T data;

    private RestReult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private RestReult() {
    }

    public static <T> RestReult<T> successEmpty() {
        return new RestReult<>(200, "success", null);
    }



    public static <T> RestReult<T> successData(T data) {
        return new RestReult<>(200, "success", data);
    }



    public static <T> RestReult<T> success(int code, String msg, T data) {
        return new RestReult<>(code, msg, data);
    }


}
