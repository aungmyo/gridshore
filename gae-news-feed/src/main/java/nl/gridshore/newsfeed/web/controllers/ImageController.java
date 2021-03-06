package nl.gridshore.newsfeed.web.controllers;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to obtain an Image object based on the provided image id's. There are different methods
 * for handling thumbnails and normal images.
 *
 * @author Jettro Coenradie
 */
@Controller
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public String showImage(@PathVariable long id, ModelMap modelMap) {
        Image image = imageService.obtainImage(id);
        modelMap.addAttribute("image", image);
        return "imageView";
    }

    @RequestMapping(value = "/image/{id}/thumb", method = RequestMethod.GET)
    public String showThumbnailImage(@PathVariable long id, ModelMap modelMap) {
        Image image = imageService.obtainThumbnailImage(id);
        modelMap.addAttribute("image", image);
        // if this attribute is added, we show the thumb in the view
        modelMap.addAttribute("thumb", "yes");
        return "imageView";
    }

}
