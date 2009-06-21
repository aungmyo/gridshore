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

public class EnquiryDefJpaDaoIntegrationTest extends AbstractPersistenceTest {

    private EnquiryDefJpaDao testSubject;

    public void testSaveAndDeleteEnquiryDef() throws NoSuchFieldException, IllegalAccessException {
        EnquiryDef enquiry = createSimpleEnquiry();

        testSubject.insert(enquiry);
        assertNotNull(enquiry.getId());
        assertNotNull(sharedEntityManager.find(EnquiryDef.class, enquiry.getId()));

        sharedEntityManager.flush();
        sharedEntityManager.clear();
        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef "));
        assertEquals(2, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef WHERE enquiry_id = ?", new Object[]{enquiry.getId()}));
        assertEquals(3, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef"));
        assertEquals(2, jdbcTemplate.queryForInt("SELECT count(*) FROM ChoiceDef"));
        assertEquals(enquiry.getTitle(), jdbcTemplate.queryForObject("SELECT title FROM EnquiryDef", String.class));

        // the enquiry is properly saved, but we want to know if the final field can be injected
        EnquiryDef enquiry2 = sharedEntityManager.find(EnquiryDef.class, enquiry.getId());
        assertEquals(enquiry.getTitle(), enquiry2.getTitle());

        // the enquiry is properly saved. Now we delete the enquiry, and it should not leave any traces
        testSubject.delete(sharedEntityManager.getReference(EnquiryDef.class, enquiry.getId()));
        sharedEntityManager.flush();

        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef "));
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef"));
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM ChoiceDef"));
    }

    public void setEnquiryDefJpaDao(final EnquiryDefJpaDao enquiryDefJpaDao) {
        this.testSubject = enquiryDefJpaDao;
    }
}
