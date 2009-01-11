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

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.repository.EnquiryDefRepository;
import nl.gridshore.enquiry.input.EnquiryInstance;
import nl.gridshore.rdm.utils.DomainContext;
import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

public class EnquiryContext extends DomainContext {

    private EnquiryDefRepository enquiryDefRepository;
    private List<EnquiryDef> createdEnquiryDefs = new ArrayList<EnquiryDef>();
    private List<EnquiryInstance> createdEnquiryInstances = new ArrayList<EnquiryInstance>();

    protected EnquiryContext(final DomainContextFactory<EnquiryContext> domainContextFactory) {
        super(domainContextFactory);
    }

    public EnquiryDefRepository getEnquiryDefRepository() {
        return enquiryDefRepository;
    }

    public EnquiryDef createEnquiryDefForUpdate() {
        final EnquiryDef def = new EnquiryDef();
        createdEnquiryDefs.add(def);
        enquiryDefRepository.save(def);
        return def;
    }

    public EnquiryInstance createEnquiryInstance(EnquiryDef enquiryDef) {
        EnquiryInstance enquiryInstance = new EnquiryInstance(enquiryDef);
        createdEnquiryInstances.add(enquiryInstance);
        return enquiryInstance;
    }

    @Override
    protected void prepareClose() {
        for(EnquiryDef def : createdEnquiryDefs) {
            enquiryDefRepository.save(def);
        }
        for(EnquiryInstance enquiryInstance : createdEnquiryInstances) {
            // TODO: Save enquiryInstances
        }
    }

    @Autowired
    public void setEnquiryDefRepository(final EnquiryDefRepository enquiryDefRepository) {
        this.enquiryDefRepository = enquiryDefRepository;
    }
}
