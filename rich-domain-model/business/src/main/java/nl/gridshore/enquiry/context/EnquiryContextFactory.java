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
package nl.gridshore.enquiry.context;

import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EnquiryContextFactory extends DomainContextFactory<EnquiryContext> {

    private ApplicationContext applicationContext;

    @Override
    public EnquiryContext initializeContext(final DomainContextFactory<EnquiryContext> domainContextFactory) {
        final EnquiryContext enquiryContext = new EnquiryContext(domainContextFactory);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(enquiryContext);
        return enquiryContext;
    }

    @Autowired
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
