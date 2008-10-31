package nl.gridshore.stability.circuitbreaker;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Collections;
import java.util.Map;

@Aspect
public class AnnotatedMethodCircuitBreakerInterceptor {

    private final Map<String, CircuitBreaker> circuitBreakers;

    public AnnotatedMethodCircuitBreakerInterceptor(final Map<String, CircuitBreaker> circuitBreakers) {
        this.circuitBreakers = Collections.unmodifiableMap(circuitBreakers);
    }

    @Pointcut(value = "@annotation(monitored)", argNames = "monitored")
    public void executionOfMonitoredMethod(Monitored monitored) {
    }

    @Around(value = "executionOfMonitoredMethod(monitored)", argNames = "pjp,monitored")
    public Object executeAndMonitor(ProceedingJoinPoint pjp, Monitored monitored) throws Throwable {

        String methodName = pjp.getStaticPart().getSignature().getName();
        CircuitBreaker relevantCircuitBreaker = circuitBreakers.get(monitored.value());
        if (relevantCircuitBreaker == null) {
            throw new IllegalStateException("There is no CircuitBreaker with key: " + monitored.value());
        }
        relevantCircuitBreaker.registerCall(methodName, pjp.getTarget(), pjp.getArgs());
        try {
            Object retVal = pjp.proceed();
            relevantCircuitBreaker.registerSuccessfulCall(methodName, pjp.getTarget(), retVal, pjp.getArgs());
            return retVal;
        }
        catch (Exception ex) {
            relevantCircuitBreaker.registerFailedCall(methodName, pjp.getTarget(), ex, pjp.getArgs());
            throw ex;
        }
    }

}
