package nl.gridshore.demo.webservice.security;

import org.springframework.ws.soap.security.wss4j.callback.AbstractWsPasswordCallbackHandler;
import org.springframework.ws.soap.security.wss4j.callback.UsernameTokenPrincipalCallback;
import org.apache.ws.security.WSPasswordCallback;
import org.w3c.dom.Element;

import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class PasswordCallbackHandler extends AbstractWsPasswordCallbackHandler {

    protected void handleUsernameToken(final WSPasswordCallback wsPasswordCallback) throws IOException, UnsupportedCallbackException {
        Element token = wsPasswordCallback.getCustomToken();
    }

    protected void handleUsernameTokenUnknown(final WSPasswordCallback wsPasswordCallback) throws IOException, UnsupportedCallbackException {
        super.handleUsernameTokenUnknown(wsPasswordCallback);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void handleUsernameTokenPrincipal(final UsernameTokenPrincipalCallback usernameTokenPrincipalCallback) throws IOException, UnsupportedCallbackException {
        super.handleUsernameTokenPrincipal(usernameTokenPrincipalCallback);    //To change body of overridden methods use File | Settings | File Templates.
    }

    
}
