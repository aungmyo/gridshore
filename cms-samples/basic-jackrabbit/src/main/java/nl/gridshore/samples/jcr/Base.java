/*
 * Copyright (c) 2009. Gridshore
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.samples.jcr;

import org.apache.jackrabbit.core.TransientRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jcr.*;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 * <p>Class inspired by the following blog post</p>
 * {@see http://www.theserverside.com/tt/articles/article.tss?l=JCRPract}
 * <p>This class uses a TransientRepository. This is a class provided by Apache Jackrabbit, offering a proxy to the
 * repository. It starts up the repository automatically when the first session is opened, and automatically stops
 * the repository when the last session is closed.</p>
 * <p>The repository is initialized in the constructor. Each subclass must implement the <code>doRun</code> method. To
 * make sure the repository is initialized and close correctly, use the run method.</p>
 * <p>This class is for demonstratio purposes only and is not thread safe since each instance contains a link to the
 * repository, strange locking problems might occur if multiple instances are created.</p>
 */
public abstract class Base {
    static Logger logger = LoggerFactory.getLogger(Base.class);

    private Repository repository;
    private Session holder;

    /**
     * Default constructor that initialises the proxy to the repository and creates the holder session that keeps
     * the repository open.
     */
    public Base() {
        try {
            repository = new TransientRepository();
            holder = repository.login();
        } catch (RepositoryException re) {
            logger.error("problem while creating a repository",re);
        } catch (IOException e) {
            logger.error("problem while creating a repository",e);
        }

        logger.debug("repository started");
    }

    /**
     * Method to be called when running the application
     */
    public void run() {
        try {
            doRun();
        } catch (Exception e) {
            logger.error("Problem during run",e);
        } finally {
            teardown();
        }
    }

    /**
     * Method to be overridden by subclasses that do the actual work with the repository
     * @throws Exception Thrown when something goes wrong while interacting with the repository
     */
    protected abstract void doRun() throws Exception;

    /**
     * Returns a read only session to be used by the subclass. Session need to be returned using the
     * <code>logout</code> method of this class.
     * @return Session for readonly purposes
     * @throws RepositoryException Thrown when something goes wrong during the login process
     */
    protected Session getReadonlySession() throws RepositoryException {
        return repository.login();
    }

    /**
     * Returns a session that can be used to store new or changed nodes in the repository. Sessions need to be
     * returned using the <code>logout</code> method.
     * @return Session that can be used for updating the repository.
     * @throws RepositoryException Thrown when something goes wrong during logging in to the repository.
     */
    protected Session getSession() throws RepositoryException {
        return repository.login(new SimpleCredentials("username", "password".toCharArray()));
    }

    /**
     * Disposes the session
     * @param session Session that is not to be used anymore
     */
    protected void logout(Session session) {
        session.logout();
    }

    /**
     * close the holder session
     */
    private void teardown() {
        holder.logout();
        logger.debug("repository closed");
    }
}
