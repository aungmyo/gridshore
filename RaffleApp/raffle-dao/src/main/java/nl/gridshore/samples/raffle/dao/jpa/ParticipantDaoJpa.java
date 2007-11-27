package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.domain.Participant;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2007
 * Time: 10:53:53 PM
 * Dataaccess for Participnt related actions
 */
public class ParticipantDaoJpa extends BaseDaoJpa<Participant> implements ParticipantDao {
    public ParticipantDaoJpa() {
        super(Participant.class, "Participant");
    }

    public List<Participant> loadByFilter(Participant entityFilter) {
        throw new UnsupportedOperationException();
    }

    public Participant loadByExample(Participant entity) {
        throw new UnsupportedOperationException();
    }
}
