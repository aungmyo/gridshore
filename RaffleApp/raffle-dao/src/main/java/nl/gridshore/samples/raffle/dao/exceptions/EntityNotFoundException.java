package nl.gridshore.samples.raffle.dao.exceptions;

import nl.gridshore.samples.raffle.domain.BaseDomain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 10, 2008
 * Time: 10:11:03 PM
 * Exception thrown if an entity was expected to be found but in reality was not
 */
public class EntityNotFoundException extends RaffleDaoException {
    public <T extends BaseDomain> EntityNotFoundException(Class<T> entityType, Long entityId) {
        super(createMessage(entityType, entityId));
    }

    private static <T extends BaseDomain> String createMessage(Class<T> entityType, Long entityId) {
        String className = entityType.getName();
        return "Error loading entity " + className + " with id " + entityId;
    }
}
