package nl.gridshore.samples.raffle.dao;

import nl.gridshore.samples.raffle.domain.Participant;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2007
 * Time: 10:52:32 PM
 * Participant data access component
 */
@Repository
public interface ParticipantDao extends BaseDao<Participant> {
}
