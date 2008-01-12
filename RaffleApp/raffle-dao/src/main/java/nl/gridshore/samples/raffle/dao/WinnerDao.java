package nl.gridshore.samples.raffle.dao;

import nl.gridshore.samples.raffle.domain.Winner;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 12, 2008
 * Time: 8:55:56 AM
 * Dataaccess specification for accessing winner data
 */
@Repository
public interface WinnerDao extends BaseDao<Winner> {
}
