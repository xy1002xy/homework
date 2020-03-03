package com.huyibo.springcommon.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import javax.servlet.ServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by huyibo on 2020/3/3.
 */
@Slf4j
@Aspect
@Configuration
public class LogAspect {

    private static final Long spendTime = 10000L;

    @Around("@annotation(com.huyibo.springcommon.log.CostTime)")
    public Object methodCostTimeCul(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        Long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        Long end = System.currentTimeMillis();
        log.info("spend time {}", String.valueOf(end - start));

        if (spendTime <= (end - start)) {
            String methodName = signature.getName();
            Map argsMap = getParamMap(joinPoint);
            log.warn("{} 方法响应慢，一共耗时 {} .. mills，入参为 {}",
                    methodName, String.valueOf(end - start), String.valueOf(argsMap));
        }
        return result;

    }

    private Map getParamMap(JoinPoint joinPoint) {
        Map<String, Object> content = new LinkedHashMap<String, Object>();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscovere = new LocalVariableTableParameterNameDiscoverer();
        Method method = getMethod(joinPoint);
        String[] parameterNames = parameterNameDiscovere.getParameterNames(method);
        Object[] args = joinPoint.getArgs();

        if (parameterNames == null) {
            return null;
        }
        if (args.length == parameterNames.length) {
            for (int i = 0, len = args.length; i < len; i++) {
                if (args[i] instanceof ServletRequest)
                    continue;
                content.put(parameterNames[i], args[i]);
            }
        }
        return content;
    }

    private Method getMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
            log.error("getMethod {}", e.getMessage(), e);
        }
        return method;
    }
}
