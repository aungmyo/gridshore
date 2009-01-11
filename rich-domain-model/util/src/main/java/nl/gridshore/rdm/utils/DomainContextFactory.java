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

import java.util.Stack;

public abstract class DomainContextFactory<T extends DomainContext> {

    private final ThreadLocal<Stack<T>> contextHolder = new ThreadLocal<Stack<T>>();

    public abstract T initializeContext(DomainContextFactory<T> contextFactory);

    public T createContext() {
        T currentContext = initializeContext(this);
        Stack<T> stack = contextHolder.get();
        if (stack == null) {
            stack = new Stack<T>();
            contextHolder.set(stack);
        }
        stack.push(currentContext);
        return currentContext;
    }

    public T getCurrentContext() {
        T currentContext = null;
        Stack<T> stack = contextHolder.get();
        if (stack != null && !stack.empty()) {
            currentContext = stack.peek();
        }
        return currentContext;
    }

    protected void closeContext(DomainContext domainContext) {
        Stack<T> stack = contextHolder.get();
        if (stack == null || stack.isEmpty()) {
            throw new DomainContextException("Closed a context more than once. Make sure every context is correctly closed in a finally block");
        }
        T popped = stack.pop();
        if (popped != domainContext) {
            throw new DomainContextException("Closing contexts in a different order than they were craeted. Are you sure all nested contexts are correctly closed?");
        }
    }
}
