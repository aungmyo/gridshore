package nl.gridshore.newsfeed;

import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domainservice.NewsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>Special bean to load some test data.</p>
 *
 * @author Jettro Coenradie
 */
@Component
public class Bootstrap implements InitializingBean {

    @Autowired
    private NewsService newsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        newsService.createNewsItem("Jettro Coenradie",new Date(),"First version rss feed application is live",
                "We needed an application capable of thousands of rss feeds under high load, caching was important",
                "This application demonstrates the solution as we are using it. Check the blogpost at gridshore to find out more");
        newsService.createNewsItem("Jettro Coenradie",new Date(),"Bootstrapper added",
                "Now we have a bootstrapper just like grails",
                "Oke you cannot use the groovy goodness, but this is nice as well");

        // since we use an internal database we know the ideas that are created
        newsService.addComment(1L,"Gridshore","Is this sample hosted somewhere?");
        newsService.addComment(1L,"Jettro", "Nope sorry, know of a free tomcat hoster? Maybe I'll try google app engine in the future");
    }
}
