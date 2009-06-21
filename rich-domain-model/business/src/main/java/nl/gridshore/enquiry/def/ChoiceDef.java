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
import org.hibernate.annotations.Cascade;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class ChoiceDef extends BaseEntity {

    @ManyToOne(optional = false)
    private MultipleChoiceQuestionDef questionDef;

    @OneToMany(mappedBy = "parentChoiceDef", cascade = CascadeType.ALL)
    @OrderBy("index")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<QuestionDef> subQuestionDefs = new ArrayList<QuestionDef>();

    @Column(name = "ordering")
    private int index;

    @Column
    private String text;

    public ChoiceDef(final String text) {
        this.text = text;
    }

    public ChoiceDef(final String text, List<QuestionDef> subQuestions) {
        this(text);
        int i = 0;
        for (QuestionDef question : subQuestions) {
            Assert.isNull(question.getEnquiry());
            question.setIndex(i++);
            question.setParentChoiceDef(this);
            subQuestionDefs.add(question);
        }
    }

    // ======================== Setter/Getter ===============================

    public EnquiryDef getEnquiry() {
        if (questionDef == null) {
            return null;
        }
        return questionDef.getEnquiry();
    }

    public List<QuestionDef> getSubQuestions() {
        return Collections.unmodifiableList(subQuestionDefs);
    }

    public MultipleChoiceQuestionDef getQuestionDef() {
        return questionDef;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public String getPath() {
        return questionDef.getPath() + "." + getIndex();
    }

    // ======================== Helper methods ==============================

    QuestionDef getSubQuestionByPath(final int[] path) {
        int[] subPath = Arrays.copyOfRange(path, 1, path.length);
        return subQuestionDefs.get(path[0]).getSubQuestionByPath(subPath);
    }

    void setQuestionDef(final MultipleChoiceQuestionDef questionDef) {
        this.questionDef = questionDef;
    }

    void setIndex(final int index) {
        this.index = index;
    }

    ChoiceDef() {
        // needed for Hibernate
    }
}
