package kr.unlike.spring.aop;

import kr.unlike.spring.dto.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Aspect
public class LoggerAspect {

    @Around("within(@org.springframework.stereotype.Controller *) || " +
            "within(@org.springframework.web.bind.annotation.RestController *)")
    public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Method method = methodSignature.getMethod();
        log.info("CONTROLLER - {}", joinPoint.getTarget().getClass().getSimpleName() + "." + method.getName() + "()");

        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error("CONTROLLER - [ERROR] {}", msg);
            throw e;
        }

        if (log.isDebugEnabled()) {
            if (result instanceof ApiResponse) {
                String response = result.toString();
                if (response.length() > 1000) {
                    response = response.substring(0, 1000) + " ::: SKIP";
                }
                log.info("CONTROLLER - [RESPONSE] {}", response);
            }
        }
        return result;
    }

    @Around("execution(* *..dao.*.*(..))")
    public Object dao(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String methodName = joinPoint.getSignature().getName();
        List<Object> args = new ArrayList<>();
        Collections.addAll(args, joinPoint.getArgs());
        log.info("DAO - {}", getTargetObject(joinPoint.getThis()) + "." + methodName + "()");
        log.debug("DAO - [PARAMS] : {}", args);
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error("DAO - [ERROR] {}", msg);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("DAO - [RESPONSE] {} ", result);
            }
            stopWatch.stop();
        }

        long total = stopWatch.getTotalTimeMillis();
        log.info(("DAO - [ExecutionTime] " + getTargetObject(joinPoint.getThis()) + "." + methodName) + "() , " + total + "(ms)");

        return result;
    }

    @Around("execution(* *..service..*.*(..))")
    public Object service(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        log.info("SERVICE - {}", joinPoint.getTarget().getClass().getSimpleName() + "." + methodName);
        log.debug("SERVICE - [PARAMS] {}", args);
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error("SERVICE - [ERROR] {}", msg);
            throw e;
        }

        if (log.isDebugEnabled()) {
            log.debug("SERVICE - [RESPONSE] {} ", result);
        }

        return result;
    }

    protected String getTargetObject(Object proxy) {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return (((Advised) proxy).getProxiedInterfaces())[0].getSimpleName();
        } else {
            return proxy.toString();
        }
    }
}
