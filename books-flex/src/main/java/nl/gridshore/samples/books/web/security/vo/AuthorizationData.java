package nl.gridshore.samples.books.web.security.vo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: May 6, 2008
 * Time: 8:59:43 PM
 * Value object used to pass security data back to the client to be used
 */
public class AuthorizationData implements Serializable {
    private String username;
    private String[] roles;

    public AuthorizationData(String[] roles, String username) {
        this.roles = roles;
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
