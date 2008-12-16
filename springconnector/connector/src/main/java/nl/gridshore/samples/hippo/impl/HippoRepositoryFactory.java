package nl.gridshore.samples.hippo.impl;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.hippoecm.repository.HippoRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 26, 2008
 * Time: 10:18:12 PM
 * <p>Spring FactoryBean used to construct a <code>HippoRepository</code> bean. The factory can be created using the
 * default path rmi://localhost:1099/hipporepository. You can also create the factory with your own connector string</p>
 *
 * <p>Due to the static initialization of the hippo repository and the easy structure of this class, there is no test
 * for this class.</p>
 */
public class HippoRepositoryFactory implements FactoryBean, InitializingBean {
    private String location = "rmi://localhost:1099/hipporepository";

    /**
     * Default constructor, now the default connector string will be used.
     */
    public HippoRepositoryFactory() {
    }

    /**
     * Constructor that is provided to supply your own connector string
     * @param location <code>String</code> containing the default constructor
     */
    public HippoRepositoryFactory(String location) {
        if (null != location && !"".equals(location)) {
            this.location = location;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getObject() throws Exception {
        return org.hippoecm.repository.HippoRepositoryFactory.getHippoRepository();
    }

    /**
     * {@inheritDoc}
     */
    public Class getObjectType() {
        return HippoRepository.class;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSingleton() {
        return true;
    }

    /**
     * <p>Used to set the default repository of the hippo repository</p>
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        org.hippoecm.repository.HippoRepositoryFactory.setDefaultRepository(location);
    }
}
