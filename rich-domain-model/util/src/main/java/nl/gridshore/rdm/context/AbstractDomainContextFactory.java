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
package nl.gridshore.rdm.context;

import java.util.Stack;

public abstract class AbstractDomainContextFactory<T extends DomainContext> implements DomainContextFactory<T> {

    private final ThreadLocal<Stack<T>> contextHolder = new ThreadLocal<Stack<T>>();

    /**
     * Create and initialize a DomainContext instance. This instance does not have to be thread safe.
     *
     * @return a fully initialized DomainContext instance.
     */
    protected abstract T initializeContext();

    /**
     * {@inheritDoc}
     */
    public T createContext() {
        T currentContext = initializeContext();
        Stack<T> stack = contextHolder.get();
        if (stack == null) {
            stack = new Stack<T>();
            contextHolder.set(stack);
        }
        stack.push(currentContext);
        return currentContext;
    }

    /**
     * {@inheritDoc}
     */
    public T getCurrentContext() {
        T currentContext = null;
        Stack<T> stack = contextHolder.get();
        if (stack != null && !stack.empty()) {
            currentContext = stack.peek();
        }
        return currentContext;
    }

    /**
     * {@inheritDoc}
     */
    public void removeContext(DomainContext domainContext) {
        if (domainContext.isOpen()) {
            throw new IllegalDomainContextStateException("The provided domainContext has not been marked for closing. Make sure close() is called on the DomainContext");
        }
        Stack<T> stack = contextHolder.get();
        if (stack == null || stack.isEmpty()) {
            throw new IllegalDomainContextStateException("Closed a context more than once. Make sure every context is correctly closed in a finally block");
        }
        T popped = stack.pop();
        if (popped != domainContext) {
            throw new IllegalDomainContextStateException("Closing contexts in a different order than they were craeted. Are you sure all nested contexts are correctly closed?");
        }
    }
}
