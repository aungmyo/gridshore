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
