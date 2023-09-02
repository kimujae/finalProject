package com.kujproject.kuj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//public class JpaQueryTimeLogger {
//
//    private long startTime;
//
//    @Before("execution(* com.kujproject.kuj.repository.*(..))")
//    public void beforeQueryExecution(JoinPoint joinPoint) {
//        startTime = System.currentTimeMillis();
//    }
//
//    @After("execution(* com.kujproject.kuj.repository.*(..))")
//    public void afterQueryExecution(JoinPoint joinPoint) {
//        long endTime = System.currentTimeMillis();
//        long executionTime = endTime - startTime;
//
//        String methodName = joinPoint.getSignature().toShortString();
//        System.out.println(methodName + " executed in " + executionTime + "ms");
//    }
//}