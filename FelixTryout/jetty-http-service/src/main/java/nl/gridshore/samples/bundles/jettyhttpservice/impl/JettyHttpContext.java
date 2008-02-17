package nl.gridshore.samples.bundles.jettyhttpservice.impl;

import org.osgi.service.http.HttpContext;
import org.osgi.framework.Bundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 9:00:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class JettyHttpContext implements HttpContext {
    private Bundle bundle;

    public JettyHttpContext(Bundle bundle) {
        this.bundle = bundle;
    }

    public boolean handleSecurity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public URL getResource(String name) {
        return bundle.getResource(name);
    }

    public String getMimeType(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
