package nl.gridshore.samples.books.web.security.security;

import flex.messaging.FlexContext;

import java.security.Principal;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import nl.gridshore.samples.books.web.security.vo.AuthorizationData;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jul 8, 2008
 * Time: 10:36:57 PM
 * Class used to interact with the spring context and do the actual authentication.
 */
public class AuthenticationHelper {
    /**
     * Checks if the current user is authenticated by evaluating the FlexContext
     * @return Boolean true if the current user is authenticated and false elsewise
     */
    public boolean principleIsAuthenticated() {
        Principal userPrincipal = FlexContext.getUserPrincipal();
        return userPrincipal != null;

    }

    /**
     * Takes the username and password as provided and checks the validaty of the credentials. Spring security is used to
     * check the credentielas and to return the authenticated principal with it's authorized roles.
     * @param username
     * @param password
     * @return
     */
    public AuthorizationData authenticatePrincipal(String username, String password) {
        ApplicationContext appContext =
                WebApplicationContextUtils.getWebApplicationContext(FlexContext.getServletConfig().getServletContext());
        AuthenticationManager manager = (AuthenticationManager)appContext.getBean("_authenticationManager");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication = manager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        GrantedAuthority[] authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        int numAuthorities = authorities.length;
        String[] grantedRoles = new String[numAuthorities];
        for (int counter = 0; counter < numAuthorities ; counter++) {
            grantedRoles[counter] = authorities[counter].getAuthority();
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return new AuthorizationData(grantedRoles,name);
    }
}
