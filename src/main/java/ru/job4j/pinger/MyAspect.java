package ru.job4j.pinger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.icmp4j.IcmpPingResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(MyTestAnnotation) && args(list,..)")
    public void callAtMyServiceAnnotation(List<IcmpPingResponse> list) { }

    @Before("callAtMyServiceAnnotation(list)")
    public void beforeCallAtMethod1(JoinPoint jp, List<IcmpPingResponse> list) {
        System.out.println("before size");
        list.forEach(System.out::println);
    }

    @After("callAtMyServiceAnnotation(list)")
    public void afterCallAt(JoinPoint jp, List<IcmpPingResponse> list) {
        System.out.println("after size");
    }
}
