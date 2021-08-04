package com.mphasis.books.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/*
 * This class provides centralized logging mechanism through @Aspect
 */
@Component
@Aspect
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut="execution(* com.mphasis.books.*.*.*(..))",throwing
			="exception")
	public void logAfterThrowingAdvice(JoinPoint joinpoint, Exception exception) {
		logger.info("In after throwing advice, Joinpoint signature :{}",joinpoint.getSignature());
		logger.info(exception.getMessage());
	}
}
