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
package nl.gridshore.enquiry.input;

import nl.gridshore.rdm.persistence.BaseEntity;
import nl.gridshore.enquiry.def.EnquiryDef;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class EnquiryInstance extends BaseEntity {

    @ManyToOne(optional = false)
    private EnquiryDef enquiryDef;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiryInstance")
    private List<AnswerInstance> answerInstances;

    protected EnquiryInstance() {
    }

    public EnquiryInstance(final EnquiryDef enquiryDef) {
        this.enquiryDef = enquiryDef;
    }

    public List<AnswerInstance> getAnswerInstances() {
        return answerInstances;
    }

    public EnquiryDef getEnquiryDef() {
        return enquiryDef;
    }
}
