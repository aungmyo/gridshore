package nl.gridshore.samples.raffle.dao;

import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 23:50:57
 * Data access object for the Raffle objects
 */
@Repository
public interface RaffleDao extends BaseDao<Raffle> {
}
