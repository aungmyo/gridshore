package nl.gridshore.samples.books.web.view;

import org.springframework.web.servlet.view.AbstractView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * <p>This view class uses the provided map by key <strong>exposedParams</strong>. Using this map,
 * a file is streamed to the client with content type <strong>text/plain</strong>. All found key
 * value pairs in the map are written back as a lines in the file.</p>
 * <p> check {@link nl.gridshore.samples.books.web.controller.PropertyController PropertyController} for implementation specifics.</p>
 * @author jettro coenradie
 *         Date: Jan 23, 2009
 */
public class PropertyView extends AbstractView {
    private static Logger logger = LoggerFactory.getLogger(PropertyView.class);

    public PropertyView() {
        setContentType("text/plain");
    }

    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("Render view for requested resource {}",request.getRequestURI());
        Map<String,String> exposedParams = (Map<String,String>)model.get("exposedParams");

        Set<Map.Entry<String,String>> entries = exposedParams.entrySet();

        response.setContentType(getContentType());
        for(Map.Entry<String,String> entry : entries) {
            StringBuilder lineBuilder = new StringBuilder();
            lineBuilder.append(entry.getKey());
            lineBuilder.append("=");
            lineBuilder.append(entry.getValue());
            response.getWriter().println(lineBuilder.toString());
        }
    }
}
