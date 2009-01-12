/*
 * Copyright (c) 2008 JTeam B.V.
 * www.jteam.nl
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JTeam B.V. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with JTeam.
 */
package nl.gridshore.rdm.utils;

public interface DomainContextFactory<T extends DomainContext> {

    /**
     * Create a new context. The calling code is responsible for correctly closing this context.
     *
     * @return a new instance of a {@link nl.gridshore.rdm.utils.DomainContext}
     */
    T createContext();

    /**
     * Get the current {@link nl.gridshore.rdm.utils.DomainContext}. If multiple nested contexts are available, the last created is returned. If there
     * are no contexts open for this factory, <code>null</code> is returned.
     * <p>{@link nl.gridshore.rdm.utils.DomainContext} instances obtained this way should no be closed.
     *
     * @return an existing {@link nl.gridshore.rdm.utils.DomainContext} instance, if any.
     */
    T getCurrentContext();

    /**
     * Removes the given {@link nl.gridshore.rdm.utils.DomainContext} from the stack. This method should not be called directly, but only via
     * the {@link nl.gridshore.rdm.utils.DomainContext#close()} method. Implementations of this method must check the
     * {@link nl.gridshore.rdm.utils.DomainContext#isOpen()} method to verify the context itself has been correctly
     * closed().
     *
     * @param domainContext The {@link nl.gridshore.rdm.utils.DomainContext} to remove from the stack
     */
    void removeContext(DomainContext domainContext);
}
