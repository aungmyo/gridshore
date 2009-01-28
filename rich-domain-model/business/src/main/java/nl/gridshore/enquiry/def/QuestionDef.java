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
    protected String questionText;

    @ManyToOne(optional = true)
    protected EnquiryDef enquiry;

    @Column(name = "ordering")
    protected int index;

    @ManyToOne(optional = true)
    @JoinColumn
    protected ChoiceDef parentChoiceDef;

    public EnquiryDef getEnquiry() {
        if (parentChoiceDef != null) {
            return parentChoiceDef.getEnquiry();
        }
        return enquiry;
    }

    void setEnquiry(final EnquiryDef enquiry) {
        if (parentChoiceDef != null) {
            throw new IllegalStateException("Cannot add sub questions to an Enquiry Definition directly");
        }
        this.enquiry = enquiry;
    }

    void setParentChoiceDef(final ChoiceDef parentChoiceDef) {
        if (this.enquiry != null) {
            throw new IllegalStateException("Cannot add as sub question when added to an Enquiry Definition directly");
        }
        this.parentChoiceDef = parentChoiceDef;
    }

    protected void setIndex(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
