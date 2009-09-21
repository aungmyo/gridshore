package nl.gridshore.newsfeed.web;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import net.sf.ehcache.management.ManagementService;

import javax.management.MBeanServer;
import javax.servlet.FilterConfig;
import java.lang.management.ManagementFactory;

public class CacheCompleteResponseMonitoringFilter extends SimplePageCachingFilter {
    @Override
    public void doInit(FilterConfig filterConfig) throws CacheException {
        super.doInit(filterConfig);
        CacheManager manager = CacheManager.getInstance();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(manager, mBeanServer, false, false, false, true);
    }
}
