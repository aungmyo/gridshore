package nl.gridshore.newsfeed.web.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
        modelMap.addAttribute("stories", stories);
        return "news/list";
    }

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap) {
        modelMap.addAttribute("story", new NewsItemVO());
        return "news/form";
    }


}
