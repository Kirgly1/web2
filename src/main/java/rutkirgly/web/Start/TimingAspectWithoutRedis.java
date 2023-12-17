//package rutkirgly.web.Start;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//@Aspect
//@Component
//public class TimingAspectWithoutRedis {
//    @Around("execution(* rutkirgly.web.Services.*.*(..))")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        Object result = joinPoint.proceed();
//
//        stopWatch.stop();
//        System.out.println(joinPoint.getSignature() + " executed in " + stopWatch.getTotalTimeMillis() + " ms");
//
//        return result;
//    }
//}
