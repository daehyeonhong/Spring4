package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		log.info(":::::::::::::::::::::::");

	}

	@Before("execution(* org.zerock.service.SampleService*.doAdd(String,String))&&args(str1,str2)")
	public void logBeforeWithParameter(String str1, String str2) {
		log.info("Str1" + str1);
		log.info("Str2" + str2);
	}

	@Around("execution(* org.zerock.service.Sample*.*(..))")
	public Object logTime(ProceedingJoinPoint joinPoint) {
		long start = System.currentTimeMillis();

		log.info("Target ==> " + joinPoint.getTarget());
		log.info("param ==> " + Arrays.toString(joinPoint.getArgs()));

		Object result = null;

		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		log.info("Time" + (end - start));

		return result;
	}

}
