package nl.gridshore.newsfeed.web.controllers;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.domain.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jettro Coenradie
 */
@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public String showImage(@PathVariable long id, ModelMap modelMap) {
        Image image = imageService.obtainImage(id);
        modelMap.addAttribute("image",image);
        return "imageView";
    }
}
