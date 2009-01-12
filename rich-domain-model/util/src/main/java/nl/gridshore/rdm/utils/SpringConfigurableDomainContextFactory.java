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
package nl.gridshore.rdm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Constructor;

/**
 * Spring configurable implementation of the DomainContextFactory.
 *
 * @param <T> The type of DomainContext that is created by this factory.
 */
public class SpringConfigurableDomainContextFactory<T extends DomainContext> extends AbstractDomainContextFactory<T>
        implements org.springframework.context.ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Class<T> domainContextType;

    @Override
    public T initializeContext(final DomainContextFactory<T> domainContextFactory) {
        final T enquiryContext;
        try {
            Constructor<T> constructor = domainContextType.getDeclaredConstructor(DomainContextFactory.class);
            enquiryContext = constructor.newInstance(domainContextFactory);
        } catch (Exception e) {
            throw new DomainContextException("Unable to initialize DomainContext.", e);
        }
        applicationContext.getAutowireCapableBeanFactory().autowireBean(enquiryContext);
        return enquiryContext;
    }

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setDomainContextType(Class<T> domainContextType) {
        this.domainContextType = domainContextType;
    }
}
