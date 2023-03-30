package com.example.demo.utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

public class ExceptionUtils extends Throwable {

    private static final long serialVersionUID = 2292536724643030659L;

    //自定义异常
    public static class ForbiddenException extends RuntimeException {
        private static final long serialVersionUID = 59151123040099746L;

        public ForbiddenException(String message) {
            super(message);
        }
    }

    //自定义异常
    public static class BusinessException extends RuntimeException {
        private static final long serialVersionUID = -1797687458577903837L;

        public BusinessException(String message) {
            super(message);
        }
    }

    //统一拦截异常
    @RestControllerAdvice(basePackages = "com.example.demo")
    public static class ExceptionAdvice {

        /**
         * 捕获 {@code BusinessException} 异常
         */
        @ExceptionHandler({BusinessException.class})
        public ResultUtils<?> handleBusinessException(BusinessException ex) {
            return ResultUtils.failed(ex.getMessage());
        }

        /**
         * 捕获 {@code ForbiddenException} 异常
         */
        @ExceptionHandler({ForbiddenException.class})
        public ResultUtils<?> handleForbiddenException(ForbiddenException ex) {
            return ResultUtils.failed(ResultEnum.FORBIDDEN);
        }

        /**
         * {@code @RequestBody} 参数校验不通过时抛出的异常处理
         */
        @ExceptionHandler({MethodArgumentNotValidException.class})
        public ResultUtils<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
            BindingResult bindingResult = ex.getBindingResult();
            StringBuilder sb = new StringBuilder("校验失败:");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
            }
            String msg = sb.toString();
            if (StringUtils.hasText(msg)) {
                return ResultUtils.failed(ResultEnum.VALIDATE_FAILED.getCode(), msg);
            }
            return ResultUtils.failed(ResultEnum.VALIDATE_FAILED);
        }

        /**
         * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
         */
        @ExceptionHandler({ConstraintViolationException.class})
        public ResultUtils<?> handleConstraintViolationException(ConstraintViolationException ex) {
            if (StringUtils.hasText(ex.getMessage())) {
                return ResultUtils.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
            }
            return ResultUtils.failed(ResultEnum.VALIDATE_FAILED);
        }

        /**
         * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
         */
        @ExceptionHandler({Exception.class})
        public ResultUtils<?> handle(Exception ex) {
            String res="";
            if (ex.getMessage() == null) {
                res="请求失败";
            }
            else {
                res=ex.getMessage();
            }
            return ResultUtils.failed(res);
        }

    }
}
