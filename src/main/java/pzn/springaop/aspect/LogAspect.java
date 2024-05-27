package pzn.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("target(pzn.springaop.service.HelloService)")
    public void helloServiceMethod() {

    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodaAme = joinPoint.getSignature().getName();
        log.info("Before " + className + "." + methodaAme + "()");
    }

//    @Before("helloServiceMethod()")
//    public void beforeHelloServiceMethod2() {
//        log.info("Before Hello Service Method Again");
//    }

    @Around("helloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodaAme = joinPoint.getSignature().getName();
        try {
            log.info("Around Before " + className + "." + methodaAme + "()");
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info("Around Error " + className + "." + methodaAme + "()");
            throw throwable;
        } finally {
            log.info("Around Finally " + className + "." + methodaAme + "()");
        }
    }

    @Pointcut("execution(* pzn.springaop.service.HelloService.*(java.lang.String))")
    public void pointcutHelloServiceStringParam() {

    }

//    @Before("pointcutHelloServiceStringParam()")
//    public void logStringParameter(JoinPoint joinPoint) {
//        String value = (String) joinPoint.getArgs()[0];
//        log.info("Execute method with parameter : " + value);
//    }

    @Before("pointcutHelloServiceStringParam() && args(name)")
    public void logStringParameter(String name) {
//        String value = (String) joinPoint.getArgs()[0];
        log.info("Execute method with parameter : " + name);
    }

    @Pointcut("execution(* pzn.springaop.service.*.*(..))")
    public void pointcutServicePackage() {

    }

    @Pointcut("bean(*Service)")
    public void pointcutServiceBean() {

    }

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod() {

    }

    @Pointcut("pointcutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService() {

    }

    @Pointcut("publicMethodForService()")
    public void logAllPublicServiceMethod() {
        log.info("Log for all public service method");
    }
}
