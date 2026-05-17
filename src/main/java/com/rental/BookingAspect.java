package com.rental;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookingAspect {

    // @Around advice to intercept and manage the booking flow
    @Around("execution(* com.rental.RentalCarBooking.bookCar(..))")
    public Object aroundBooking(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[AROUND - Before] Intercepting " + methodName + " for customer: " + args[0]);
        Object result = joinPoint.proceed();
        System.out.println("[AROUND - After] " + methodName + " completed successfully.");
        return result;
    }

    // @AfterReturning advice to log details after successful car release
    @AfterReturning(pointcut = "execution(* com.rental.RentalCarBooking.releaseCar(..))", returning = "result")
    public void afterReturningRelease(JoinPoint joinPoint, Object result) {
        System.out.println("[AFTER RETURNING] Car released successfully. Details: " + result);
    }

    // @After advice to print a thank-you message after release, regardless of outcome
    @After("execution(* com.rental.RentalCarBooking.releaseCar(..))")
    public void afterRelease(JoinPoint joinPoint) {
        System.out.println("[AFTER] Thank you for using our Rental Car Service!");
    }

    // @AfterThrowing advice to handle and display error messages when exceptions occur
    @AfterThrowing(pointcut = "execution(* com.rental.RentalCarBooking.*(..))", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[AFTER THROWING] Exception in " + methodName + ": " + exception.getMessage());
    }
}
