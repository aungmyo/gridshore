package nl.gridshore.newsfeed.integration.image;

import com.google.appengine.api.images.dev.LocalImagesService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import static junit.framework.Assert.assertEquals;
import nl.gridshore.TestEnvironment;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ImageConversionServiceTest {
    private static LocalImagesService imagesService;
    private ImageConversionService service;

    @BeforeClass
    public static void initClass() {
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        ApiProxy.setDelegate(new ApiProxyLocalImpl(new File("./target")) {
        });
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        imagesService = (LocalImagesService)proxy.getService("images");
        imagesService.start();
    }

    @AfterClass
    public static void tearDownClass() {
        imagesService.stop();
        ApiProxy.setDelegate(null);
        ApiProxy.setEnvironmentForCurrentThread(null);
    }

    @Before
    public void init() {
        service = new ImageConversionService();
    }

    @Test
    public void createThumbnail() throws IOException {
        byte[] bijtje = readByteArrayFromFile();

        byte[] thumbnail = service.createThumbnail(bijtje);

        checkSizeThubnail(thumbnail,ImageConversionService.DEFAULT_THUMBNAIL_SIZE);
    }

    @Test
    public void createThumbnailCustomSize() throws IOException {
        byte[] bijtje = readByteArrayFromFile();

        byte[] thumbnail = service.createThumbnail(bijtje,75);
        checkSizeThubnail(thumbnail,75);
    }

    private void checkSizeThubnail(byte[] thumbnail, int expectedSize) throws IOException {
        //noinspection AppEngineForbiddenCode
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(thumbnail));
        assertEquals(expectedSize,image.getWidth());
    }

    private byte[] readByteArrayFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("bijtje.jpg");
        File file = resource.getFile();
        byte[] b = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(b);
        return b;
    }
}
