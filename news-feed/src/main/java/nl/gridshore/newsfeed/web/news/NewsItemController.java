package nl.gridshore.newsfeed.web.news;

import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domainservice.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/news/**")
public class NewsItemController {
    private static final Logger logger = LoggerFactory.getLogger(NewsItemController.class);

    @Autowired
    private NewsService newsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "news/list";
    }

    @RequestMapping(value = "/news/feed.rss", method = RequestMethod.GET)
    public String rss(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "newsRssFeed";
    }


    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap) {
        modelMap.addAttribute("newsItem", new NewsItemVO());
        return "news/form";
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String create(@ModelAttribute("newsItem") NewsItemVO newsItem, BindingResult result) {
        Assert.notNull(newsItem, "A news item instance must be provided");
        new NewsValidator().validate(newsItem, result);

        if (result.hasErrors()) {
            return "news/form";
        } else {
            this.newsService.createNewsItem(newsItem.getAuthor(), newsItem.getPublicationDate(),
                    newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
            return "redirect:/news";
        }
    }

}
