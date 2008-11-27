package nl.gridshore.samples.hippo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.hippoecm.repository.HippoRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:18:12 PM
 * Spring FactoryBean used to construct a <code>HippoRepository</code> bean, the object can be created using a default path
 * of the hippo repository or with a to beprovided location.
 */
public class HippoRepositoryFactory implements FactoryBean, InitializingBean {
    private String location = "rmi://localhost:1099/hipporepository"; 

    public HippoRepositoryFactory() {
    }

    public HippoRepositoryFactory(String location) {
        if (null != location && !"".equals(location)) {
            this.location = location;
        }
    }

    public Object getObject() throws Exception {
        return org.hippoecm.repository.HippoRepositoryFactory.getHippoRepository();
    }

    public Class getObjectType() {
        return HippoRepository.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        org.hippoecm.repository.HippoRepositoryFactory.setDefaultRepository(location);
    }
}
