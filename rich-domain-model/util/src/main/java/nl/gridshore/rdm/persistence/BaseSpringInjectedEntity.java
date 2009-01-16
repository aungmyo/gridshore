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

import nl.gridshore.rdm.context.ApplicationContextHolder;

import javax.persistence.MappedSuperclass;

/**
 * Implementation of a {@link nl.gridshore.rdm.persistence.BaseEntity} that automatically wires the entity during
 * construction. This class requires that the {@link nl.gridshore.rdm.context.ApplicationContextHolder} is wired inside
 * the Spring Application Context.
 *
 * @see nl.gridshore.rdm.context.ApplicationContextHolder
 */
@MappedSuperclass
public abstract class BaseSpringInjectedEntity extends BaseEntity {

    protected BaseSpringInjectedEntity() {
        if (ApplicationContextHolder.isApplicationContextAvailable()) {
            ApplicationContextHolder.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
        }
    }
}
