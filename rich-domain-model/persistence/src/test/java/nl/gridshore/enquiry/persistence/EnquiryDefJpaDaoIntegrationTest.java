package nl.gridshore.enquiry.persistence;

import nl.gridshore.enquiry.def.EnquiryDef;

public class EnquiryDefJpaDaoIntegrationTest extends AbstractPersistenceTest {

    private EnquiryDefJpaDao testSubject;

    public void testSaveAndDeleteEnquiryDef() {
        EnquiryDef enquiry = createSimpleEnquiry();

        testSubject.insert(enquiry);
        assertNotNull(enquiry.getId());
        assertNotNull(sharedEntityManager.find(EnquiryDef.class, enquiry.getId()));

        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef "));
        assertEquals(2, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef WHERE enquiry_id = ?", new Object[]{enquiry.getId()}));
        assertEquals(3, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef"));
        assertEquals(2, jdbcTemplate.queryForInt("SELECT count(*) FROM ChoiceDef"));

        // the enquiry is properly saved. Now we delete the enquiry, and it should not leave any traces
        testSubject.delete(enquiry);
        sharedEntityManager.flush();

        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef "));
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef"));
        assertEquals(0, jdbcTemplate.queryForInt("SELECT count(*) FROM ChoiceDef"));
    }

    public void setEnquiryDefJpaDao(final EnquiryDefJpaDao enquiryDefJpaDao) {
        this.testSubject = enquiryDefJpaDao;
    }
}
