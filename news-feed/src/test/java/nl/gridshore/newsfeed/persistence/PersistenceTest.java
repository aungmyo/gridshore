package nl.gridshore.newsfeed.persistence;

import static junit.framework.Assert.assertEquals;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:persistence-spring-config.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class PersistenceTest {
    @Autowired
    protected NewsItemRepository repo;

    @Test
    public void createNewsItem() {
        NewsItem newsItem = new NewsItem("jettro", new Date(),
                "Caching and rss", "Something about how to implement caching while serving rss",
                "This is the complete item text, this can become long");
        newsItem.tag("Caching");
        newsItem.tag("RSS");
        newsItem.tag("Spring 3");
        repo.addNewsItem(newsItem);

        List<NewsItem> newsItems = repo.listAllNewsItems();
        assertEquals("Number of items returned are not correct", 1, newsItems.size());
        NewsItem foundNewsItem = newsItems.get(0);
        assertEquals("Item does not have the right tags", 3, foundNewsItem.metaData().tags().length);
        assertEquals("Tag is not right", "Caching", foundNewsItem.metaData().tags()[0]);
        assertEquals("Tag is not right", "RSS", foundNewsItem.metaData().tags()[1]);
        assertEquals("Tag is not right", "Spring 3", foundNewsItem.metaData().tags()[2]);
    }
}
