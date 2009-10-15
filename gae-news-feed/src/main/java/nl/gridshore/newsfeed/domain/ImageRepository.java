package nl.gridshore.newsfeed.domain;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public interface ImageRepository {
    long persist(Image image);
    Image obtainImageById(long id);
    List<Image> listAllImages();
}
