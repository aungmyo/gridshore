package nl.gridshore.newsfeed.service.impl;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.domain.ImageRepository;
import nl.gridshore.newsfeed.integration.image.ImageConversionService;
import nl.gridshore.newsfeed.service.ImageService;
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
        // TODO create a real service object and dependency injection
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
