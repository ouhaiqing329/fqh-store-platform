package com.fqh.utils.response;

import com.fqh.utils.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 统一结果
 *
 * @author fqh
 * @date 2022/08/14
 */
@Data
@Builder
public class BaseResponseResult<T> {

    private int code;

    private String desc;

    private String msg;

    private T data;

    public static <T> BaseResponseResult<T> baseResult(ResultEnum resultEnum,String msg,T data){
        return (BaseResponseResult<T>) BaseResponseResult.builder()
                .code(resultEnum.code)
                .desc(resultEnum.desc)
                .msg(msg)
                .data(data)
                .build();
    }

    public static <T> BaseResponseResult<T> success(){
        return baseResult(ResultEnum.SUCCESS,null,null);
    }

    public static <T> BaseResponseResult<T> success(String msg){
        return baseResult(ResultEnum.SUCCESS,msg,null);
    }

    public static <T> BaseResponseResult<T> success(T data){
        return baseResult(ResultEnum.SUCCESS,null,data);
    }

    public static <T> BaseResponseResult<T> success(String msg,T data){
        return baseResult(ResultEnum.SUCCESS,msg,data);
    }
    public static <T> BaseResponseResult<T> error(){
        return baseResult(ResultEnum.ERROR,null,null);
    }

    public static <T> BaseResponseResult<T> error(String msg){
        return baseResult(ResultEnum.ERROR,msg,null);
    }

    public static <T> BaseResponseResult<T> error(T data){
        return baseResult(ResultEnum.ERROR,null,data);
    }

    public static <T> BaseResponseResult<T> error(String msg,T data){
        return baseResult(ResultEnum.ERROR,msg,data);
    }

}
