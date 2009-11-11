package nl.gridshore;

import com.google.apphosting.api.ApiProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * project test environment
 */
public class TestEnvironment implements ApiProxy.Environment {
    /**
     * get GAE app id
     *
     * @return app id
     */
    public String getAppId() {
        return "doingjava";
    }

    /**
     * get version id
     *
     * @return version
     */
    public String getVersionId() {
        return "2";
    }

    public String getRequestNamespace() {
        return "";
    }

    public Map<String, Object> getAttributes() {
        return new HashMap<String, Object>();
    }

    public boolean isLoggedIn() {
        throw new UnsupportedOperationException();
    }

    public String getEmail() {
        throw new UnsupportedOperationException();
    }

    public boolean isAdmin() {
        throw new UnsupportedOperationException();
    }

    public String getAuthDomain() {
        throw new UnsupportedOperationException();
    }
}
