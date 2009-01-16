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
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanNameAware;
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
        implements ApplicationContextAware, BeanFactoryPostProcessor, BeanNameAware {

    private ApplicationContext applicationContext;
    private Class<T> domainContextType;
    private String beanName;

    /**
     * Creates a new DomainContext of type <code>domainContextType</code> and autowires it using
     * the current <code>applicationContext</code>.
     *
     * @return A new wired instance of type <code>domainContextType</code>
     */
    @Override
    protected T initializeContext() {
        final T enquiryContext;
        try {
            Constructor<T> constructor = domainContextType.getDeclaredConstructor(DomainContextFactory.class);
            enquiryContext = constructor.newInstance(this);
        } catch (Exception e) {
            throw new DomainContextException("Unable to initialize DomainContext.", e);
        }
        applicationContext.getAutowireCapableBeanFactory().autowireBean(enquiryContext);
        return enquiryContext;
    }

    /**
     * The type (class) of {@link nl.gridshore.rdm.utils.DomainContext} that is created by this factory. This can be
     * any class that extends {@link nl.gridshore.rdm.utils.DomainContext} and has a constructor accepting a
     * {@link nl.gridshore.rdm.utils.DomainContextFactory} as sole parameter.
     *
     * @param domainContextType The type managed by this factory
     * @see nl.gridshore.rdm.utils.DomainContext
     */
    public void setDomainContextType(Class<T> domainContextType) {
        this.domainContextType = domainContextType;
    }

    /**
     * Set the bean name of this context factory. This method is called by the Spring Framework
     *
     * @param name The bean name of this context factory.
     * @see org.springframework.beans.factory.BeanFactory
     */
    @Override
    public void setBeanName(final String name) {
        this.beanName = name;
    }

    /**
     * Set the application context this instance is loaded in. This method is automatically called by the Spring
     * Framework.
     *
     * @param applicationContext The current {@link org.springframework.context.ApplicationContext}
     * @see org.springframework.context.ApplicationContextAware
     * @see org.springframework.beans.factory.BeanFactory
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Registers a {@link nl.gridshore.rdm.context.DomainContextProxyFactoryBean} in the current
     * {@link org.springframework.context.ApplicationContext}. The name for the bean is the name of the
     * {@link nl.gridshore.rdm.context.SpringConfigurableDomainContextFactory} suffixed with ".ContextFactoryBean". A
     * {@link org.springframework.beans.factory.BeanCreationException} is thrown if a bean with that name already exists
     * in the application context.
     *
     * @param beanFactory The {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory} of the
     *                    current context
     * @throws BeanCreationException when the factory bean cannot be created.
     * @see org.springframework.beans.factory.BeanFactory
     */
    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeanCreationException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        String factoryBeanName = beanName + ".ContextFactoryBean";
        if (registry.isBeanNameInUse(factoryBeanName)) {
            throw new BeanCreationException(String.format("Unable to create DomainContextProxyFactoryBean for %s. A bean with name %s already exists", domainContextType, factoryBeanName));
        }
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(DomainContextProxyFactoryBean.class);
        bdb.addConstructorArgValue(this)
                .addConstructorArgValue(domainContextType);
        registry.registerBeanDefinition(factoryBeanName, bdb.getBeanDefinition());
    }
}
