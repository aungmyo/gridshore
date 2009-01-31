package nl.gridshore.samples.jcr;

import org.apache.jackrabbit.core.TransientRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 29, 2009
 * Time: 9:44:06 AM
 * http://www.theserverside.com/tt/articles/article.tss?l=JCRPract
 * <p/>
 * TransientRepository. This is a class provided by Apache Jackrabbit, offering a proxy to the repository. It starts
 * up the repository automatically when the first session is opened, and automatically stops the repository when the
 * last session is closed.
 */
public abstract class Base {
    static Logger logger = LoggerFactory.getLogger(Base.class);
    private static Repository repository;
    private static Session holder;

    static {
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

    public void run() {
        try {
            doRun();
        } catch (Exception e) {
            logger.error("Problem during run",e);
        } finally {
            teardown();
        }
    }

    protected abstract void doRun() throws Exception;

    public Session getReadonlySession() throws RepositoryException {
        return repository.login();
    }

    public Session getSession() throws RepositoryException {
        return repository.login(new SimpleCredentials("username", "password".toCharArray()));
    }

    public void logout(Session session) {
        session.logout();
    }

    public void teardown() {
        holder.logout();
        logger.debug("repository closed");
    }
}
