package nl.gridshore.newsfeed.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public interface ImageService {
    @Transactional
    List<Image> listAllImages();

    @Transactional
    long createImage(String filename, String contentType, byte[] content);

    @Transactional
    Image obtainImage(long id);

}
