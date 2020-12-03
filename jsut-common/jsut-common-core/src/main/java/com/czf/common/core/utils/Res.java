package com.czf.common.core.utils;

import com.czf.common.core.constants.HttpStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: Scott
 */

@Data
@Accessors(chain = true)
public class Res<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer code;

    private T data;

    public static <T> Res<T> success() {
        return responseResult(HttpStatus.SUCCESS, "success", null);
    }

    public static <T> Res<T> success(T data) {
        return responseResult(HttpStatus.SUCCESS, "success", data);
    }

    public static <T> Res<T> success(String msg, T data) {
        return responseResult(HttpStatus.SUCCESS, msg, data);
    }

    public static <T> Res<T> failed() {
        return responseResult(HttpStatus.FAILED, null, null);
    }

    public static <T> Res<T> failed(T data) {
        return responseResult(HttpStatus.FAILED, null, data);
    }

    public static <T> Res<T> failed(String msg, T data) {
        return responseResult(HttpStatus.FAILED, msg, data);
    }



    private static <T> Res<T> responseResult(Integer code, String msg, T data) {

        Res<T> result = new Res<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);

        return result;
    }

}
