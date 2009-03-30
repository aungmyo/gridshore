package nl.gridshore.enquiry.persistence;

import nl.gridshore.enquiry.def.AnswerLength;
import nl.gridshore.enquiry.def.ChoiceDef;
import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import nl.gridshore.enquiry.def.QuestionDef;
import org.springframework.test.jpa.AbstractJpaTests;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPersistenceTest extends AbstractJpaTests {
    @Override
    protected String[] getConfigLocations() {
        return new String[]{"META-INF/spring/persistence-context.xml"};
    }

    public void onSetUpInTransaction() {
        clearEnquiryDefs();
    }

    @SuppressWarnings({"unchecked"})
    private void clearEnquiryDefs() {
        List<EnquiryDef> defs = sharedEntityManager.createQuery("SELECT q FROM EnquiryDef q").getResultList();
        for (EnquiryDef def : defs) {
            sharedEntityManager.remove(def);
        }
        sharedEntityManager.flush();
    }

    /**
     * Creates an enquiry instance with an open question and a multiple choice question with two options. The second
     * option has an open sub question.
     *
     * @return a simple Enquiry structure for test purposes
     */
    protected EnquiryDef createSimpleEnquiry() {
        List<QuestionDef> questions = new ArrayList<QuestionDef>();
        questions.add(new OpenQuestionDef("Question 1", AnswerLength.SINGLE_LINE));
        List<ChoiceDef> choices = new ArrayList<ChoiceDef>();
        choices.add(new ChoiceDef("Some text"));
        List<QuestionDef> subQuestions = new ArrayList<QuestionDef>();
        subQuestions.add(new OpenQuestionDef("Give some details", AnswerLength.SINGLE_LINE));
        choices.add(new ChoiceDef("Option requiring additional info", subQuestions));
        questions.add(new MultipleChoiceQuestionDef("Make a choice", choices));
        return new EnquiryDef("My new enquiry", questions);
    }
}
