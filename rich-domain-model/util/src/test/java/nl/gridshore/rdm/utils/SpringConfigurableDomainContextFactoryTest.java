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

import net.sf.cglib.proxy.Enhancer;
import nl.gridshore.rdm.context.DomainContextException;
import nl.gridshore.rdm.context.NoContextAvailableException;
import nl.gridshore.rdm.context.SpringConfigurableDomainContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.Map;

public class SpringConfigurableDomainContextFactoryTest extends AbstractDependencyInjectionSpringContextTests {

    private SpringConfigurableDomainContextFactory<MockDomainContext> factory;

    public void onSetUp() {
        // these tests change the state of beans, so we have to setup the application context for each test
        setDirty();
    }

    public void testFactoryCreatesAndManagesContexts() {
        assertNull("Shouldn't initialize context on getCurrentContext()", factory.getCurrentContext());
        MockDomainContext context = factory.createContext();
        assertNotNull("Should have initialized a new context", context);
        assertSame(context, factory.getCurrentContext());
        MockDomainContext innerContext = factory.createContext();
        assertNotSame(context, innerContext);
        innerContext.close();
        context.close();
        assertNull("Should have removed all contexts from factory", factory.getCurrentContext());
    }

    public void testClosingContextsInWrongOrder() {
        MockDomainContext context = factory.createContext();
        MockDomainContext innerContext = factory.createContext();
        try {
            context.close();
            fail("Expected an exception to be thrown");
        }
        catch (DomainContextException dce) {
            assertTrue(dce.getMessage().contains("order"));
        }
    }

    @SuppressWarnings({"unchecked"})
    public void testCurrentContextAvailableAsBean() {
        Map<String, MockDomainContext> beans = applicationContext.getBeansOfType(MockDomainContext.class);
        assertEquals(1, beans.size());
        MockDomainContext context = beans.entrySet().iterator().next().getValue();

        assertTrue(Enhancer.isEnhanced(context.getClass()));
        try {
            context.isOpen();
            fail("Expected exception");
        }
        catch (NoContextAvailableException ex) {
            assertTrue(ex.getMessage().contains("MockDomainContext"));
        }
        factory.createContext();
        Map<String, MockDomainContext> beans2 = applicationContext.getBeansOfType(MockDomainContext.class);
        assertEquals(1, beans.size());
        MockDomainContext context2 = beans2.entrySet().iterator().next().getValue();
        assertTrue(Enhancer.isEnhanced(context2.getClass()));

        assertEquals(factory.getCurrentContext().hashCode(), context2.hashCode());
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:context/factory-testcontext.xml"};
    }

    @Autowired
    public void setFactory(final SpringConfigurableDomainContextFactory<MockDomainContext> factory) {
        this.factory = factory;
    }
}
