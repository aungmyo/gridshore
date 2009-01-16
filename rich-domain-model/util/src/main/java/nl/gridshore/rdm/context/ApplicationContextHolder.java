/*
 * Copyright (c) 2008 JTeam B.V.
 * www.jteam.nl
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JTeam B.V. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with JTeam.
 */
package nl.gridshore.rdm.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Utility class that can be configured inside a Spring Application Context to make that context available throughout
 * the application statically.
 * <p/>
 * <p>A bean of this type is required when using the {@link nl.gridshore.rdm.persistence.BaseSpringInjectedEntity}.
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Get the current Application Context.
     *
     * @return the current ApplicationContext
     * @throws IllegalStateException if no context has been set in this holder
     */
    public static ApplicationContext getApplicationContext() {
        if (!isApplicationContextAvailable()) {
            throw new IllegalStateException("There is no application context in the holder. " +
                    "Are you sure you have configured the ApplicationConextHolder in your application context?");
        }
        return applicationContext;
    }

    /**
     * Returns whether an Application has been configured in this Holder. A value of false typically means that no bean
     * of type {@link nl.gridshore.rdm.context.ApplicationContextHolder} was configured, or the call was made before
     * the ApplicationContext is started up.
     *
     * @return true if an {@link org.springframework.context.ApplicationContext} is set in this holder, otherwise false
     */
    public static boolean isApplicationContextAvailable() {
        return applicationContext != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}
