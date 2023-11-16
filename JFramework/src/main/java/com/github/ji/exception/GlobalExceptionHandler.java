package com.github.ji.exception;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.ji.core.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


/**
 * 全局异常处理器，将 Exception 翻译成 CommonResult + 对应的异常编号
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        logger.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return CommonResult.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public CommonResult bindExceptionHandler(BindException ex) {
        logger.warn("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return CommonResult.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return CommonResult.error(GlobalErrorCode.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        logger.warn("[noHandlerFoundExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCode.NOT_FOUND.getCode(), String.format("请求地址不存在:%s", ex.getRequestURL()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        logger.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCode.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理Spring Assert异常
     * <p>
     * 例如Assert.isTrue(1==1,"1不等于1");
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonResult illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        logger.warn("[IllegalArgumentException]", ex);
        return CommonResult.error(GlobalErrorCode.SERVICE_ERROR.getCode(), ex.getMessage())
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }


    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        logger.error("[defaultExceptionHandler]", ex);
        // 返回 ERROR CommonResult
        return CommonResult.error(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(), GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }


}
