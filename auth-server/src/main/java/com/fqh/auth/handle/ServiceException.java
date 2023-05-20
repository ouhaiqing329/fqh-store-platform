package com.fqh.auth.handle;

import com.fqh.utils.enums.ResultEnum;

/**
 * @author ouhaiqing
 * @date 2022/9/5 16:24
 */
public class ServiceException  extends BaseExceptionHandle {
    private static final long serialVersionUID = 5561171040132769889L;

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
