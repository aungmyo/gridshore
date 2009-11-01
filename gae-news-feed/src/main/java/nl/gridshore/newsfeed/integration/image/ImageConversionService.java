package nl.gridshore.newsfeed.integration.image;

/**
 * <p>Implementations are used to create conversions of provided images.</p>
 *
 * @author Jettro Coenradie
 */
public interface ImageConversionService {

    /**
     * Create a thumbnail with the default thumbnail size
     *
     * @param original byte[] containing the data of the provided image
     * @return byte[] containing the data for the created thumbnail
     */
    byte[] createThumbnail(byte[] original);

    /**
     * Create a thumbnail with the provided thumbnail size.
     *
     * @param original            byte[] containing the data of the provided image
     * @param customThumbnailSize int representing the custom size of the thumbnail to create
     * @return byte[] containing the data for the created thumbnail
     */
    byte[] createThumbnail(byte[] original, int customThumbnailSize);
}
