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
package nl.gridshore.enquiry.def;

import nl.gridshore.rdm.persistence.BaseEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 8)
@Table(name = "QuestionDef")
public abstract class QuestionDef extends BaseEntity {

    @Column
    private String questionText;

    @ManyToOne(optional = false)
    private EnquiryDef enquiry;

    @Column(name = "ordering")
    private long index;

    @ManyToOne(optional = true)
    @JoinColumn
    private ChoiceDef parentChoiceDef;

    public EnquiryDef getEnquiry() {
        if (enquiry == null) {
            return parentChoiceDef.getEnquiry();
        }
        return enquiry;
    }

    void setEnquiry(final EnquiryDef enquiry) {
        if (parentChoiceDef != null) {
            throw new IllegalStateException("Cannot add sub questions to and Enquiry Definition directly");
        }
        this.enquiry = enquiry;
    }

    public void setIndex(final int index) {
        this.index = index;
    }
}
