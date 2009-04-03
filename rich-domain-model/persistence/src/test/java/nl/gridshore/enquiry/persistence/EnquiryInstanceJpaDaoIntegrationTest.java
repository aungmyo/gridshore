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

package nl.gridshore.enquiry.persistence;

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import nl.gridshore.enquiry.input.EnquiryInstance;
import nl.gridshore.enquiry.input.OpenAnswerInstance;

public class EnquiryInstanceJpaDaoIntegrationTest extends AbstractPersistenceTest {

    private EnquiryInstanceJpaDao testSubject;

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"META-INF/spring/persistence-context.xml"};
    }

    public void testEnquiryInstanceLifeCycle() {
        EnquiryDef enquiry = createSimpleEnquiry();
        sharedEntityManager.persist(enquiry);

        EnquiryInstance enquiryInstance = new EnquiryInstance(enquiry);
        enquiryInstance.addAnswer(new OpenAnswerInstance((OpenQuestionDef) enquiry.getQuestions().get(0), "This is my answer"));

        testSubject.insert(enquiryInstance);
        sharedEntityManager.flush();
        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryInstance"));
        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM AnswerInstance"));

        enquiryInstance.addAnswer(new OpenAnswerInstance((OpenQuestionDef) enquiry.getQuestions().get(0), "This is another answer"));
        testSubject.update(enquiryInstance);
        sharedEntityManager.flush();
        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryInstance"));
        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM AnswerInstance"));
        assertEquals("This is another answer", jdbcTemplate.queryForObject("SELECT text FROM AnswerInstance", String.class));

        testSubject.delete(enquiryInstance);
        sharedEntityManager.flush();
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryInstance"));
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM AnswerInstance"));
    }

    public void setEnquiryInstanceJpaDao(EnquiryInstanceJpaDao enquiryInstanceJpaDao) {
        this.testSubject = enquiryInstanceJpaDao;
    }
}
