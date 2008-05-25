package nl.gridshore.samples.books.web.security.impl;

import nl.gridshore.samples.books.web.security.BooksSecurityServices;
import nl.gridshore.samples.books.web.security.vo.AuthorizationData;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.GrantedAuthority;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Apr 28, 2008
 * Time: 10:19:04 PM
 * Implementation using Acegi specific classes to obtain information about authorizations
 */
public class BooksSecurityServicesImpl implements BooksSecurityServices {
    public AuthorizationData obtainGrantedRoles() {
        GrantedAuthority[] authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        int numAuthorities = authorities.length;
        String[] grantedRoles = new String[numAuthorities];
        for (int counter = 0; counter < numAuthorities ; counter++) {
            grantedRoles[counter] = authorities[counter].getAuthority();
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new AuthorizationData(grantedRoles,username);
    }
}
