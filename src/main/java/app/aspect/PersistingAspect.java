package app.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class PersistingAspect {

    private static final Logger logger = Logger.getLogger(PersistingAspect.class);

    @Pointcut(value = "execution(public * app.dao..*(..))")
    public void daoPointcut() {
    }


    @Around(value = "daoPointcut()")
    public Object aroundDaoAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Calling dao "+ proceedingJoinPoint.toShortString() + " method: " + methodName);

        Object res = null;
        try {
            res = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("Error " + throwable.getMessage() + "\nproceeding method of dao: " + methodName);
        }

        logger.info("Method has worked: " + methodName + ", returning " + res);

        return res;
    }
}
