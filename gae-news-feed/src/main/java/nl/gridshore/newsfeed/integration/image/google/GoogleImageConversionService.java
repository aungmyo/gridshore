package nl.gridshore.newsfeed.integration.image.impl;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import nl.gridshore.newsfeed.integration.image.ImageConversionService;
import org.springframework.stereotype.Service;

/**
 * <p>Service that makes use of the Google app engine image service to handle image byte[]
 * and do certain actions on it.</p>
 * <p>The default thumbnail size is set but can be changed using the exposed property</p>
 *
 * @author Jettro Coenradie
 */
@Service
public class GoogleImageConversionService implements ImageConversionService {
    public final static int DEFAULT_THUMBNAIL_SIZE = 100;
    public final static int MINIMUM_THUMBNAIL_SIZE = 10;

    private int thumbnailSize;

    /**
     * Defaul constructor that sets the default thumbnail size
     */
    public GoogleImageConversionService() {
        thumbnailSize = DEFAULT_THUMBNAIL_SIZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] createThumbnail(byte[] original) {
        return doCreateThumbnail(original, thumbnailSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] createThumbnail(byte[] original, int customThumbnailSize) {
        int sizeToUse = customThumbnailSize;
        if (sizeToUse < MINIMUM_THUMBNAIL_SIZE) {
            sizeToUse = MINIMUM_THUMBNAIL_SIZE;
        }
        return doCreateThumbnail(original, sizeToUse);
    }

    private byte[] doCreateThumbnail(byte[] original, int thumbnailSize) {
        ImagesService imagesService = ImagesServiceFactory.getImagesService();

        Image oldImage = ImagesServiceFactory.makeImage(original);
        Transform resize = ImagesServiceFactory.makeResize(thumbnailSize, thumbnailSize);

        Image newImage = imagesService.applyTransform(resize, oldImage);

        return newImage.getImageData();
    }

    /* Getters and setters */

    /**
     * Setter for the default thumbnail size to be used when creating a thumbnail.
     *
     * @param thumbnailSize int representing the default thumbnail size to set.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public void setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }
}
