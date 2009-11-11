package nl.gridshore.newsfeed.web.tags;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 */
public class SecurityTag extends TagSupport {
    public boolean isAuthenticated() {
        return userService().isUserLoggedIn();
    }

    public boolean isAdmin() {
        return userService().isUserAdmin();
    }

    public String userName() {
        return userService().getCurrentUser().getNickname();
    }

    public String userId() {
        return userService().getCurrentUser().getUserId();
    }


    protected void write(String toWrite) throws JspException {
        try {
            pageContext.getOut().write(toWrite);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    protected UserService userService() {
        return UserServiceFactory.getUserService();
    }
}
