package com.codermi.blog.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qiudm
 * @date 2018/9/7 14:21
 * @desc
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.codermi.blog.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder sb = new StringBuilder("\n");
        sb.append("URL: ").append(request.getRequestURL().toString()).append("\n");
        sb.append("IP: ").append(request.getRemoteHost()).append("\n");
        try {
            sb.append("PARAM: ").append(JSON.toJSON(joinPoint.getArgs())).append("\n");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        sb.append("Content-Type：").append(request.getContentType()).append("\n");
        sb.append("User-Agent：").append(request.getHeader("User-Agent")).append("\n");
        sb.append("**************************************************").append("\n");

        LOGGER.info(sb.toString());
    }


    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        LOGGER.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


}
