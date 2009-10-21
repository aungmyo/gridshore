package nl.gridshore.newsfeed.integration.image;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Service that makes use of the Google app engine image service to handle image byte[] and do certain actions on it.
 *
 * The default thumbnail size is set but can be changed using the exposed property
 *
 * @author Jettro Coenradie
 */
@Component
public class ImageConversionService {
    public final static int DEFAULT_THUMBNAIL_SIZE = 100;
    public final static int MINIMUM_THUMBNAIL_SIZE = 10;

    private int thumbnailSize;

    /**
     * Defaul constructor that sets the default thumbnail size
     */
    public ImageConversionService() {
        thumbnailSize = DEFAULT_THUMBNAIL_SIZE;
    }

    /**
     * Create a thumbnail with the configure or default thumbnail size
     *
     * @param original byte[] containing the data of the provided image
     * @return byte[] containing the data for the created thumbnail
     */
    public byte[] createThumbnail(byte[] original) {
        return doCreateThumbnail(original, thumbnailSize);
    }

    /**
     * Create a thumbnail with the configure or default thumbnail size. Minimum size of the thumbnail is 10.
     *
     * @param original byte[] containing the data of the provided image
     * @param customThumbnailSize int representing the custom size of the thumbnail to create
     * @return byte[] containing the data for the created thumbnail
     */
    public byte[] createThumbnail(byte[] original,int customThumbnailSize) {
        Assert.isTrue(customThumbnailSize >= MINIMUM_THUMBNAIL_SIZE);
        return doCreateThumbnail(original, customThumbnailSize);
    }

    private byte[] doCreateThumbnail(byte[] original, int thumbnailSize) {
        ImagesService imagesService = ImagesServiceFactory.getImagesService();

        Image oldImage = ImagesServiceFactory.makeImage(original);
        Transform resize = ImagesServiceFactory.makeResize(thumbnailSize,thumbnailSize);

        Image newImage = imagesService.applyTransform(resize, oldImage);

        return newImage.getImageData();
    }

    /* Getters and setters */

    /**
     * Setter for the default thumbnail size to be used when creating a thumbnail.
     * @param thumbnailSize int representing the default thumbnail size to set.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public void setThumbnailSize(int thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }
}
