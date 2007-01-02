package nl.gridshore.samples.springosgi.impl;

import junit.framework.TestCase;
import nl.gridshore.samples.springosgi.Bean;

public class BeanImplTest extends TestCase {

    public void testBeanIsABean() {
	Bean aBean = new BeanImpl();
        assertTrue(aBean.isABean());
    }

}