package nl.gridshore.newsfeed.web.view;

import nl.gridshore.newsfeed.domain.Image;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Jettro Coenradie
 */
public class ImageView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Image image = (Image) model.get("image");
        response.setContentType(image.getContentType());
        response.getOutputStream().write(image.getContent());
    }
}
