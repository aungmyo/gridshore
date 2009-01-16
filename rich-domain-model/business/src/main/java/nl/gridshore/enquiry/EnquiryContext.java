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
package nl.gridshore.enquiry;

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.enquiry.input.EnquiryInstance;
import nl.gridshore.enquiry.repository.EnquiryDefDao;
import nl.gridshore.enquiry.repository.EnquiryInstanceDao;
import nl.gridshore.enquiry.repository.QuestionDefDao;
import nl.gridshore.rdm.utils.DomainContext;
import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EnquiryContext extends DomainContext {

    private EnquiryDefDao enquiryDefRepository;
    private EnquiryInstanceDao enquiryInstanceRepository;
    private QuestionDefDao questionDefRepository;

    public EnquiryContext(final DomainContextFactory<EnquiryContext> domainContextFactory) {
        super(domainContextFactory);
    }

    public EnquiryDef createEnquiryDefForUpdate() {
        final EnquiryDef def = new EnquiryDef();
        registerForSave(def);
        return def;
    }

    public EnquiryInstance newEnquiryInstanceForUpdate(EnquiryDef enquiryDef) {
        EnquiryInstance enquiryInstance = new EnquiryInstance(enquiryDef);
        registerForSave(enquiryInstance);
        return enquiryInstance;
    }

    public void registerForDeletion(final QuestionDef questionDef) {
        registerForDelete(questionDef, questionDefRepository);
    }

    public void registerForSave(final EnquiryDef def) {
        registerForSave(def, enquiryDefRepository);
    }

    public void registerForSave(final EnquiryInstance instance) {
        registerForSave(instance, enquiryInstanceRepository);
    }

    public EnquiryDefDao getEnquiryDefRepository() {
        return enquiryDefRepository;
    }

    // ======================== Setter/Getter ===============================

    @Autowired
    public void setEnquiryDefRepository(final EnquiryDefDao enquiryDefRepository) {
        this.enquiryDefRepository = enquiryDefRepository;
    }

    public EnquiryInstanceDao getEnquiryInstanceRepository() {
        return enquiryInstanceRepository;
    }

    @Autowired
    public void setEnquiryInstanceRepository(final EnquiryInstanceDao enquiryInstanceRepository) {
        this.enquiryInstanceRepository = enquiryInstanceRepository;
    }

    @Autowired
    public void setQuestionDefRepository(final QuestionDefDao questionDefRepository) {
        this.questionDefRepository = questionDefRepository;
    }
}
