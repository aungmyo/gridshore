package nl.gridshore.newsfeed.domain.impl;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.domain.ImageRepository;
import nl.gridshore.newsfeed.domain.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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
        Image image = new Image(filename,contentType,content);

        return imageRepository.persist(image);
    }

    @Override
    public Image obtainImage(long id) {
        Image foundImage = imageRepository.obtainImageById(id);
        foundImage.getContent();
        return foundImage;
    }
}
