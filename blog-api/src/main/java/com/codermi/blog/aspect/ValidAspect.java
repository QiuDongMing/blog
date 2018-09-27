package com.codermi.blog.aspect;

import com.codermi.common.base.utils.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author qiudm
 * @date 2018/9/26 16:02
 * @desc
 */
//@Aspect
//@Component
public class ValidAspect {

    @Pointcut("execution(public * com.codermi.blog.controller..*.*(..))")
    public void valid() {
    }

    @Around("valid()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("============lll");
        BindingResult bindingResult = null;
        for(Object arg:joinPoint.getArgs()){//遍历被通知方法(controller方法)的参数列表
            System.out.println("arg = " + arg);
            if(arg instanceof BindingResult){//参数校验结果会存放在BindingResult中
                System.out.println("args = " );
                bindingResult = (BindingResult) arg;
            }

        }
        if(bindingResult != null){
            System.out.println("bind result");
            if(bindingResult.hasErrors()){//检查是否存在校验错误
                System.out.println("error ex");
                String errorInfo = "";
                List<FieldError> errors = bindingResult.getFieldErrors();//获取字段参数不合法的错误集合
                for(FieldError error : errors){
                    errorInfo = errorInfo + "[" + error.getField() + " " + error.getDefaultMessage() + "]";
                }
//                return new ResponseBean().setExceptionResponse(errorInfo);//返回异常错误
                JsonResult jsonResult = new JsonResult();
                jsonResult.setDetailMsg(errorInfo);
                return jsonResult;
            }
        }
        return joinPoint.proceed();//执行目标方法
    }

}
