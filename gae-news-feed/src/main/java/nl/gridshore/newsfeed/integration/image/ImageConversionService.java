package nl.gridshore.newsfeed.integration.image;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * @author Jettro Coenradie
 */
public class ImageConversionService {
    public byte[] createThumbnail(byte[] original) {
        ImagesService imagesService = ImagesServiceFactory.getImagesService();

        Image oldImage = ImagesServiceFactory.makeImage(original);
        Transform resize = ImagesServiceFactory.makeResize(100,100);

        Image newImage = imagesService.applyTransform(resize, oldImage);

        return newImage.getImageData();
    }
}
