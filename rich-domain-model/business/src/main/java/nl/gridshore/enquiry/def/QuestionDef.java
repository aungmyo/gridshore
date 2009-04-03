/*
 * Copyright (c) 2009. Gridshore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.enquiry.def;

import nl.gridshore.rdm.persistence.BaseEntity;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 50)
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

    protected QuestionDef() {
    }

    protected QuestionDef(final String questionText) {
        this.questionText = questionText;
    }

    public EnquiryDef getEnquiry() {
        if (parentChoiceDef != null) {
            return parentChoiceDef.getEnquiry();
        }
        return enquiry;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getIndex() {
        return index;
    }

    void setEnquiry(final EnquiryDef enquiry) {
        Assert.isNull(parentChoiceDef, "Cannot add sub questions to an Enquiry Definition directly");
        this.enquiry = enquiry;
    }

    void setParentChoiceDef(final ChoiceDef parentChoiceDef) {
        Assert.isNull(this.enquiry, "Cannot add as sub question when added to an Enquiry Definition directly");
        this.parentChoiceDef = parentChoiceDef;
    }

    protected void setIndex(final int index) {
        this.index = index;
    }
}
