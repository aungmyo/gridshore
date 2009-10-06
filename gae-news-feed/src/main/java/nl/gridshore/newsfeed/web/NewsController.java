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


    // TODO Document hack for google app engine. This is supported by spring but usually not necessary
    @InitBinder
    protected void initBinder(HttpServletRequest request, WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
    }


    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap) {
        modelMap.addAttribute("newsItem", new NewsItemVO());
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

}
