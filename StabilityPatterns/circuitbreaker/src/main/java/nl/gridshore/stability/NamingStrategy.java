package nl.gridshore.stability;

import org.springframework.jmx.export.naming.ObjectNamingStrategy;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class NamingStrategy implements ObjectNamingStrategy {
    public ObjectName getObjectName(final Object managedBean, final String beanKey) throws MalformedObjectNameException {
        return new ObjectName("Stability:type=CircuitBreaker,name=" + beanKey);
    }
}
