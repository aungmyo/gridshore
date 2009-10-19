package nl.gridshore.newsfeed.domain.impl;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.domain.ImageRepository;
import nl.gridshore.newsfeed.domain.ImageService;
import nl.gridshore.newsfeed.integration.image.ImageConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of the {@code ImageService} that makes use of the {@code ImageConversionService}.
 *
 * @author Jettro Coenradie
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> listAllImages() {
        return imageRepository.listAllImages();
    }

    @Override
    public long createImage(String filename, String contentType,byte[] content) {
        // create the thumbnail
        byte[] thumbnail = new ImageConversionService().createThumbnail(content);

        Image image = new Image(filename,contentType,content,thumbnail);

        return imageRepository.persist(image);
    }

    @Override
    public Image obtainImage(long id) {
        Image foundImage = imageRepository.obtainImageById(id);
        foundImage.getContent();
        return foundImage;
    }

    @Override
    public Image obtainThumbnailImage(long id) {
        Image foundImage = imageRepository.obtainImageById(id);
        foundImage.getThumbnail();
        return foundImage;
    }
}
