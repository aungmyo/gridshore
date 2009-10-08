package nl.gridshore.newsfeed.web.tags;

import javax.servlet.jsp.JspException;

/**
 * @author Jettro Coenradie
 */
public class CanDeleteSecurityTag extends NewsItemSecurityTag {

    @Override
    public int doStartTag() throws JspException {
        if (isAuthenticated() && isAdmin()) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }

}
