package com.question.infra.in.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class ExceptionLoggingAspect {

    @Pointcut("within(com.question.commons.error.global..*)")
    public void onError() {

    }

    @Around(value = "com.question.infra.in.aop.logging.ExceptionLoggingAspect.onError()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        for (Object arg : pjp.getArgs()) {
            String packageNameAndMessage = arg.toString();
            String[] parse = packageNameAndMessage.split(":");

            log.error(buildMessage(parse[1], parse[0]));
        }
        return pjp.proceed(pjp.getArgs());
    }

    private String buildMessage(String errorName, String packagePath) {
        return String.format("%s 예외가 발생했습니다. 메세지:%s", packagePath, errorName);
    }
}
