package nl.gridshore.newsfeed.web;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import net.sf.ehcache.management.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.ManagementFactory;

public class CacheCompleteResponseMonitoringFilter extends SimplePageCachingFilter {
    private static final Logger log = LoggerFactory.getLogger(CacheCompleteResponseMonitoringFilter.class);

    @Override
    public void doInit(FilterConfig filterConfig) throws CacheException {
        super.doInit(filterConfig);
        CacheManager manager = CacheManager.getInstance();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(manager, mBeanServer, false, false, false, true);
    }

    @Override
    protected String calculateKey(HttpServletRequest httpRequest) {
        String key = super.calculateKey(httpRequest);
        log.info("**Calculated key for item to cache : {}",key);
        return key;
    }

    @Override
    protected String getCacheName() {
        String cacheName1 = super.getCacheName();
        log.info("** Cache name {}",cacheName1);
        return cacheName1;
    }
}
