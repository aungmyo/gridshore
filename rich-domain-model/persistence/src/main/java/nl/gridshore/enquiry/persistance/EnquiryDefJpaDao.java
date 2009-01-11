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
package nl.gridshore.enquiry.persistance;

import nl.gridshore.enquiry.repository.EnquiryDefRepository;
import nl.gridshore.enquiry.def.EnquiryDef;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EnquiryDefJpaDao implements EnquiryDefRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(final EnquiryDef enquiryDef) {
        entityManager.persist(enquiryDef);
    }
}
