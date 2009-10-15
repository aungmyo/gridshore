package nl.gridshore.newsfeed.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * There are to methods to obtain images, you should only call the obtainImage if you need the big one.
 *
 * @author Jettro Coenradie
 */
public interface ImageService {
    @Transactional
    List<Image> listAllImages();

    @Transactional
    long createImage(String filename, String contentType, byte[] content);

    @Transactional
    Image obtainImage(long id);

    @Transactional
    Image obtainThumbnailImage(long id);
}
