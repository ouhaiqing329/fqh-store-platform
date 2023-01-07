package com.fqh.utilities.handle;


import cn.hutool.core.util.StrUtil;
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
        if (StrUtil.isEmptyIfStr(ex.getMessage())){
            return BaseResponseResult.error();
        }
        return BaseResponseResult.error(ex.getMessage());
    }

}
