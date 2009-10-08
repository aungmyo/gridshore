package nl.gridshore.newsfeed.web;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap, HttpServletRequest request) {
        NewsItemVO newsItem = new NewsItemVO();
        if (request.getUserPrincipal() != null) {
            newsItem.setAuthor(request.getUserPrincipal().getName());
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
            this.newsService.createNewsItem(newsItem.getAuthor(), newsItem.getTitle(),
                    newsItem.getIntroduction(), newsItem.getItem());
            return "redirect:/spring/news";
        }
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "news/list";
    }

    /**
     * Spring uses a classloader to find the required editors that are used during the binding process. This
     * feature is not supported by google app engine. Therefore we need to provide the binders explicitly.
     *
     * @param binder WebDataBinder that is used to register the editors
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
    }


}
