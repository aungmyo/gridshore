package nl.gridshore.newsfeed.persistence;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import static junit.framework.Assert.assertEquals;
import nl.gridshore.TestEnvironment;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * TODO : try to get grip on the transaction stuff, now the order of the tests is important
 *
 * @author Jettro Coenradie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class JpaNewsItemRepositoryTest {

    @Autowired
    private NewsItemRepository repository;

    @BeforeClass
    public static void init() {
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        ApiProxy.setDelegate(new ApiProxyLocalImpl(new File("./target")) {
        });
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        proxy.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY, Boolean.TRUE.toString());
    }

    @AfterClass
    public static void tearDown() {
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        LocalDatastoreService datastoreService = (LocalDatastoreService) proxy.getService("datastore_v3");
        datastoreService.clearProfiles();
        ApiProxy.setDelegate(null);
        ApiProxy.setEnvironmentForCurrentThread(null);
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Rollback(false)
    public void insertSomething() {
        NewsItem newsItem = new NewsItem();
        newsItem.setAuthor("test author");
        newsItem.setIntroduction("This is an introduction");
        newsItem.setItem("Here you have the complete text of the article");
        newsItem.setTitle("My test item");
        repository.persist(newsItem);
    }

    @Test
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public void storeNewsItem() {
        List<NewsItem> newsItems = repository.listAllNewsItems();
        assertEquals(1, newsItems.size());
    }
    
}
