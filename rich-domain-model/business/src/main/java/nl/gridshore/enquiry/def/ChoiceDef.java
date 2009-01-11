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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
public class ChoiceDef extends BaseEntity {

    @ManyToOne(optional = false)
    private MultipleChoiceQuestionDef questionDef;

    @OneToMany(mappedBy = "parentChoiceDef", cascade = CascadeType.ALL)
    private List<QuestionDef> subQuestionDefs;

    @Column(name = "ordering")
    private long index;

    @Column
    private String text;

    public EnquiryDef getEnquiry() {
        return questionDef.getEnquiry();
    }
}
