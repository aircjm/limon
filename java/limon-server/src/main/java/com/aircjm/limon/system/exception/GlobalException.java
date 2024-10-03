package com.aircjm.limon.system.exception;

import com.aircjm.limon.system.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public Result handleException(CustomException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }
}
