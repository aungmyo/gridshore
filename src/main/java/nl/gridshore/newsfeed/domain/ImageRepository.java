package nl.gridshore.newsfeed.domain;

import java.util.List;

/**
 * Repository object for persistence related matters of {@code Image} objects
 *
 * @author Jettro Coenradie
 */
public interface ImageRepository {
    /**
     * Persist the provided image
     * @param image Image that needs to be persisted
     * @return long representing the id of the image that is persisted.
     */
    long persist(Image image);

    /**
     * Returns the image belonging to the provided id.
     *
     * @param id long representing the id of the Image object to be looked for
     * @return Image object found.
     */
    Image obtainImageById(long id);

    /**
     * Returns all available images
     *
     * @return List containing all images
     */
    List<Image> listAllImages();
}
