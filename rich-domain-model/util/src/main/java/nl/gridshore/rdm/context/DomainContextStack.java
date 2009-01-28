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

public class DomainContextStack<T> {

    private ThreadLocal<Stack<T>> stack = new ThreadLocal<Stack<T>>() {
        @Override
        protected Stack<T> initialValue() {
            return new Stack<T>();
        }
    };

    public void pushContext(T context) {
        stack.get().push(context);
    }

    public void removeContext(T context) {
        Stack<T> currentStack = stack.get();
        if (currentStack == null || currentStack.isEmpty()) {
            throw new DomainContextException("Closed a context more than once. Make sure every context is correctly closed in a finally block");
        }
        T popped = currentStack.pop();
        if (popped != context) {
            throw new DomainContextException("Closing contexts in a different order than they were craeted. Are you sure all nested contexts are correctly closed?");
        }
    }
}
