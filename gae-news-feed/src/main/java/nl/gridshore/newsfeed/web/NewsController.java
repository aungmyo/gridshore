package nl.gridshore.newsfeed.web;

import com.google.appengine.api.users.UserServiceFactory;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
public class NewsController extends GaeSpringController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap, HttpServletRequest request) {
        NewsItemVO newsItem = new NewsItemVO();
        if (request.getUserPrincipal() != null) {
            // TODO refactor into something generic with the tag
            newsItem.setNickName(UserServiceFactory.getUserService().getCurrentUser().getNickname());
            newsItem.setUserId(UserServiceFactory.getUserService().getCurrentUser().getUserId());
            newsItem.setEmail(UserServiceFactory.getUserService().getCurrentUser().getEmail());
        }
        modelMap.addAttribute("newsItem", newsItem);
        return "news/form";
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String create(@ModelAttribute("newsItem") NewsItemVO newsItem, BindingResult result) {
        Assert.notNull(newsItem, "A news item instance must be provided");

        if (result.hasErrors()) {
            return "news/form";
        } else {
            this.newsService.createNewsItem(
                    newsItem.getNickName(),newsItem.getUserId(),newsItem.getEmail(),
                    newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
            return "redirect:/gs/news";
        }
    }

    @RequestMapping(value = "/news/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, ModelMap modelMap) {
        this.newsService.discardNewsItem(id);

        modelMap.addAttribute("message","News item is discarded.");
        modelMap.addAttribute("title","news item discarded");
        return "message";
    }



    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "news/list";
    }

}
