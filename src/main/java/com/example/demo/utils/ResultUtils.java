package com.example.demo.utils;

import lombok.*;

interface IResult {
    Integer getCode();
    String getMessage();
}

//常用结果的枚举r
enum ResultEnum implements IResult {
    SUCCESS(2001, "接口调用成功"),
    VALIDATE_FAILED(2002, "参数校验失败"),
    COMMON_FAILED(2003, "接口调用失败"),
    FORBIDDEN(2004, "没有权限访问资源");

    private  Integer code;
    private  String message;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    //省略get、set方法和构造方法
}


//统一返回数据结构
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultUtils<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResultUtils<T> success(T data) {
        return new ResultUtils<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResultUtils<T> success(String message, T data) {
        return new ResultUtils<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static ResultUtils<?> failed() {
        return new ResultUtils<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
    }

    public static ResultUtils<?> failed(String message) {
        return new ResultUtils<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
    }

    public static ResultUtils<?> failed(IResult errorResult) {
        return new ResultUtils<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static <T> ResultUtils<T> instance(Integer code, String message, T data) {
        ResultUtils<T> result = new ResultUtils<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ResultUtils<?> failed(Integer code, String msg) {
        ResultUtils<?> result = new ResultUtils<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
