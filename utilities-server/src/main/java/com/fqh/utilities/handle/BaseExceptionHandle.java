package com.fqh.utilities.handle;

import com.fqh.utilities.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 运行时异常处理
 *
 * @author ouhaiqing
 * @date 2022/9/5 15:35
 */
@Slf4j
public class BaseExceptionHandle extends RuntimeException{

    private static final long serialVersionUID = -8260865607246264758L;

    public BaseExceptionHandle(ResultEnum resultEnum){
        super(resultEnum.desc);
        log.error("service exception code:{}",resultEnum.code);
    }

    public BaseExceptionHandle(String msg){
        super(msg);
    }


}
