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

import nl.gridshore.rdm.utils.AbstractDomainContextFactory;
import nl.gridshore.rdm.utils.DomainContext;
import nl.gridshore.rdm.utils.DomainContextException;
import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Constructor;

/**
 * Spring configurable implementation of the DomainContextFactory.
 *
 * @param <T> The type of DomainContext that is created by this factory.
 */
public class SpringConfigurableDomainContextFactory<T extends DomainContext> extends AbstractDomainContextFactory<T>
        implements ApplicationContextAware, BeanFactoryPostProcessor {

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

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(DomainContextProxyFactoryBean.class);
        bdb.addConstructorArgValue(this)
                .addConstructorArgValue(domainContextType);
        registry.registerBeanDefinition("newBean", bdb.getBeanDefinition());
    }
}
