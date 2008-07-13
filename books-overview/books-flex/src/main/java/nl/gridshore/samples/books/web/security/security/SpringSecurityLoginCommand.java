package nl.gridshore.samples.books.web.security.security;

import flex.messaging.security.LoginCommand;

import javax.servlet.ServletConfig;
import java.security.Principal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jul 7, 2008
 * Time: 10:57:40 PM
 * Custom login class to be used in the flex app
 */
public class SpringSecurityLoginCommand implements LoginCommand {
    public void start(ServletConfig servletConfig) {
        System.out.println(" ** start");
    }

    public void stop() {
        System.out.println(" ** stop");
    }

    public Principal doAuthentication(String s, Object o) {
        System.out.println(" ** doAuthentication");
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean doAuthorization(Principal principal, List list) {
        for(Object role : list) {
            String roleName = (String)role;

        }
        return false;
    }

    public boolean logout(Principal principal) {
        System.out.println(" ** logout");
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
