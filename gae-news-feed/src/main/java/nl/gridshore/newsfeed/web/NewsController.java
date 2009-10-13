package nl.gridshore.newsfeed.web;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap, HttpServletRequest request) {
        NewsItemVO newsItem = new NewsItemVO();

        User currentUser = userService.getCurrentUser();
        
        newsItem.setNickName(currentUser.getNickname());
        newsItem.setUserId(currentUser.getUserId());
        newsItem.setEmail(currentUser.getEmail());
        
        modelMap.addAttribute("newsItem", newsItem);
        return "news/form";
    }

    @RequestMapping(value = "/news/form/{id}", method = RequestMethod.GET)
    public String change(@PathVariable long id, ModelMap modelMap, HttpServletRequest request) {
        NewsItem newsItem = newsService.obtainNewsItem(id);
        NewsItemVO newsItemVO = new NewsItemVO();
        newsItemVO.setNickName(newsItem.getAuthor().getNickName());
        newsItemVO.setEmail(newsItem.getAuthor().getEmail());
        newsItemVO.setId(newsItem.getId());
        newsItemVO.setIntroduction(newsItem.getIntroduction());
        newsItemVO.setItem(newsItem.getItem());
        newsItemVO.setTitle(newsItem.getTitle());
        newsItemVO.setUserId(newsItem.getAuthor().getUserid());
        modelMap.addAttribute("newsItem", newsItemVO);
        return "news/form";
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String create(@ModelAttribute("newsItem") NewsItemVO newsItem, BindingResult result) {
        Assert.notNull(newsItem, "A news item instance must be provided");

        if (result.hasErrors()) {
            return "news/form";
        }
        if (newsItem.getId() != null && newsItem.getId() >= 0) {
            this.newsService.changeNewsItem(newsItem.getId(),
                    newsItem.getNickName(),newsItem.getUserId(),newsItem.getEmail(),
                    newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
        } else {
            this.newsService.createNewsItem(
                    newsItem.getNickName(),newsItem.getUserId(),newsItem.getEmail(),
                    newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
        }
        return "redirect:/gs/news";
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
