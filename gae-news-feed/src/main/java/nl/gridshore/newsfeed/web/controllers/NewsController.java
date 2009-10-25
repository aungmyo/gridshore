package nl.gridshore.newsfeed.web.controllers;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import nl.gridshore.newsfeed.domain.Author;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.service.ImageService;
import nl.gridshore.newsfeed.service.NewsService;
import nl.gridshore.newsfeed.web.formbean.NewsItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for all news related requests.
 *
 * @author Jettro Coenradie
 */
@Controller
public class NewsController extends GaeSpringController {
    private NewsService newsService;
    private ImageService imageService;
    private UserService userService;

    @Autowired
    public NewsController(NewsService newsService, ImageService imageService, UserService userService) {
        this.newsService = newsService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String form(ModelMap modelMap) {
        NewsItemVO newsItem = new NewsItemVO();

        User currentUser = userService.getCurrentUser();

        newsItem.setNickName(currentUser.getNickname());
        newsItem.setUserId(currentUser.getUserId());
        newsItem.setEmail(currentUser.getEmail());

        modelMap.addAttribute("newsItem", newsItem);
        return "news/form";
    }

    @RequestMapping(value = "/news/form/{id}", method = RequestMethod.GET)
    public String change(@PathVariable long id, ModelMap modelMap) {
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
    public String create(@ModelAttribute("newsItem") NewsItemVO newsItem, BindingResult result) throws IOException {
        Assert.notNull(newsItem, "A news item instance must be provided");

        if (result.hasErrors()) {
            return "news/form";
        }


        if (newsItem.getId() != null && newsItem.getId() >= 0) {
            this.newsService.changeNewsItem(
                    newsItem.getId(), newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
        } else {
            Author author = new Author(newsItem.getUserId(), newsItem.getNickName(), newsItem.getEmail());
            MultipartFile multipartFile = newsItem.getImage();
            if (multipartFile != null && multipartFile.getSize() > 0) {
                long image = this.imageService.createImage(
                        multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
                this.newsService.createNewsItem(author,
                        newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem(), image);
            } else {
                this.newsService.createNewsItem(author,
                        newsItem.getTitle(), newsItem.getIntroduction(), newsItem.getItem());
            }

        }
        return "redirect:/gs/news";
    }

    @RequestMapping(value = "/news/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, ModelMap modelMap) {
        this.newsService.discardNewsItem(id);

        modelMap.addAttribute("message", "News item is discarded.");
        modelMap.addAttribute("title", "news item discarded");
        return "message";
    }


    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        List<NewsItem> stories = newsService.listAllNewsItems();
        modelMap.addAttribute("newsItems", stories);
        return "news/list";
    }
}
