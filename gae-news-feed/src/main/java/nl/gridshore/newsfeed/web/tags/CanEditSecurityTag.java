package nl.gridshore.newsfeed.web.tags;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 */
public class CanEditSecurityTag extends NewsItemSecurityTag {

    @Override
    public int doStartTag() throws JspException {
        if (isAuthenticated()) {
            if ((userName().equals(newsItem().getAuthor())) || isAdmin()) {
                return EVAL_BODY_INCLUDE; 
            }
        }
        return SKIP_BODY;
    }
}
