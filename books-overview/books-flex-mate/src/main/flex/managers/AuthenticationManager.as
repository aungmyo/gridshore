package managers {
import models.AuthorizationData;
public class AuthenticationManager {
    [Bindable] public var authorizationData:AuthorizationData;

    public function AuthenticationManager() {
    }

    public function storeAuthenticationDetails(result:Object):void {
        this.authorizationData = AuthorizationData(result);
    }
}
}