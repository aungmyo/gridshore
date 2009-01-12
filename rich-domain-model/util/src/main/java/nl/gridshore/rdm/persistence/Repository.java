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
package nl.gridshore.rdm.persistence;

public interface Repository<T extends BaseEntity> {

    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public void delete(long id);

    T findById(long id);

    T load(long id);

}
