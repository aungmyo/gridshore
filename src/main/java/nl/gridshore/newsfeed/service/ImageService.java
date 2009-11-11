package nl.gridshore.newsfeed.service;

import nl.gridshore.newsfeed.domain.Image;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * There are two methods to obtain images, you should only call the obtainImage if you need the big one. The thumbnail
 * method only has the thumbnail. This is due to the way the datastore and lazy loading works. The interface does not
 * provide a way to obtain them both.
 *
 * @author Jettro Coenradie
 */
public interface ImageService {
    /**
     * Returns a list of all images. The actual content is not prefetched.
     *
     * @return List containing all image information
     */
    @Transactional
    List<Image> listAllImages();

    /**
     * Create a new image and store it in the data store. The provided byte stream is used to create a thumbnail
     * from the image.
     *
     * @param filename String containing the name of the file to create
     * @param contentType String containing the content type of the image
     * @param content byte[] containing the actual image
     * @return long containing the id of the created image.
     */
    @Transactional
    long createImage(String filename, String contentType, byte[] content);

    /**
     * Retuns an image for the provided id, only the actual image is pre-fetched. If you want to show the thumbnail you
     * need to call the {@see obtainThumbnailImage} method.
     *
     * @param id long containing the id of the image to fetch
     * @return Image belonging to the provided id
     */
    @Transactional
    Image obtainImage(long id);

    /**
     * Returns the image for the provided id, use this only for showing the thumbnail. Use the {@see obtainImage}
     * method for obtaining the actual image.
     *
     * @param id long representing the id of the image to obtain
     * @return Image belonging to the provided id.
     */
    @Transactional
    Image obtainThumbnailImage(long id);
}
