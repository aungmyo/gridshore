package nl.gridshore.samples.books.web.security;

import nl.gridshore.samples.books.web.security.vo.AuthorizationData;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Apr 28, 2008
 * Time: 10:16:30 PM
 * This interface exposes methods with respect to security that can be used by the flex application to obtain
 * information about rights.
 */
public interface BooksSecurityServices {
    AuthorizationData obtainGrantedRoles();
}
