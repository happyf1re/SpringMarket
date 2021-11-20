package com.example.springdata.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductMonitoringAspect {

    //хитрые две точки (..) -_- не мог понять, что не так минут 10.
    @Pointcut("execution(* com.example.springdata.service.ProductService.addOrUpdate(..))")
    public void monitorCreatingOrUpdatingProduct() {
    }

    @After("monitorCreatingOrUpdatingProduct()")
    public void monitorAfterCreatingOrUpdatingProduct(JoinPoint joinPoint) {
        System.out.println(joinPoint);
        System.out.println("Product successfully created or updated!!!\nBest monitoring ever!");
    }
}
