package nl.gridshore.newsfeed.web.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import nl.gridshore.newsfeed.domainservice.NewsService;
import nl.gridshore.newsfeed.domain.NewsItem;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/news/**")
public class NewsItemController {
    private static final Logger logger = LoggerFactory.getLogger(NewsItemController.class);

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "news/list";
    }

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap) {
        modelMap.addAttribute("newsItem", new NewsItemVO());
        return "news/form";
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute("newsItem") NewsItemVO newsItem, BindingResult result) {
        Assert.notNull(newsItem, "A news item instance must be provided");
//        new StoryValidator().validate(story, result);

        if (result.hasErrors()) {
            return "news/form";
        } else {
            if (newsItem.getId() == null) {
                this.newsService.createNewsItem(newsItem.getAuthor(),newsItem.getPublicationDate(),
                        newsItem.getTitle(),newsItem.getIntroduction(),newsItem.getItem());
            } else {
//                this.backlogService.changeStory(story.getId(), story.getName(), story.getDescription());
            }
            return "redirect:/news";
        }
    }

}
