package com.fqh.auth.handle;

import com.fqh.utils.response.BaseResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author fqh
 * @date 2022/08/14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public BaseResponseResult<Void> handle(Exception ex){
        log.error("exception:",ex);
        return BaseResponseResult.error(ex.getMessage());
    }

}
