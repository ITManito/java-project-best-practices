package com.dmsdbj.team3.javaprojectbestpractices.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


/**
 * @author : angel-rmm
 * @Date 2019/12/9  7:49 上午
 * @Package com.dmsdbj.team3.javaprojectbestpractices.utils.exception
 * @Version v1.0
 * @Copyright
 * @Email 18232466045@163.com
 * @Contract https://github.com/ITManito
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class WebExceptionHandler {

    //        参数校验失败异常
    @ExceptionHandler
    public ResultBean methodArgumentNotValid(BindException e) {
        log.error("参数校验失败", e);
        StringBuilder errorMessage = new StringBuilder();

        e.getAllErrors().forEach(objectError ->
                errorMessage.append(objectError.getDefaultMessage()).append(",")
        );
        return ResultBean.error(1, errorMessage.toString());
    }

    //        Json参数绑定到对象异常
    @ExceptionHandler
    public ResultBean methodArgumentNotValidForJsonArgumentResolver(MethodArgumentNotValidException e) {
//        报异常
        log.error("json参数绑定到对象失败", e);
        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(errors -> errorMessage.append(errors.getDefaultMessage()).append(","));
        return ResultBean.error(1, errorMessage.toString());
//        输出异常信息
//        返回结果
    }

    //        未知异常
    @ExceptionHandler
    public ResultBean unKnowException(Exception e) {
//        报异常
        log.error("未知异常", e);
//        返回结果
        return ResultBean.error(-999, "发生了未知异常，请联系系统管理员");
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultBean NoHandlerFoundExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("异常详情", e);
        return ResultBean.error(404, "页面不存在");
    }

}
