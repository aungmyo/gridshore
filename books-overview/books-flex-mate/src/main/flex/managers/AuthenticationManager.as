package managers {
import com.asfusion.mate.events.Dispatcher;
import events.AuthenticationEvent;
import models.AuthorizationData;
import mx.rpc.Fault;
public class AuthenticationManager {
    [Bindable] public var authorizationData:AuthorizationData;
    [Bindable] public var faultMessage:String = "";

    public function AuthenticationManager() {
    }

    public function storeAuthenticationDetails(result:Object):void {
        faultMessage = "";
        this.authorizationData = AuthorizationData(result);
    }

    public function storeAuthenticationProblem(Obj:Object):void {
        var faultObj:Fault = Fault(Obj);

        var BAD_CREDENTIALS:String =
                "org.springframework.security.BadCredentialsException : Bad credentials";

        if (faultObj.faultString == BAD_CREDENTIALS) {
            faultMessage = "Something wrong with the credentials";
        } else {
            faultMessage = "Unknown problem occurred, please try again.";
        }
    }

    public function logout():void {
        faultMessage = "";
        authorizationData = new AuthorizationData();
    }

    public function evaluateAuthorizationData(obj:Object):void {
        var event:AuthenticationEvent;
        if (obj.username != "roleAnonymous") {
            storeAuthenticationDetails(obj);
            event = new AuthenticationEvent(AuthenticationEvent.AUTHENTICATED);
        } else {
            event = new AuthenticationEvent(AuthenticationEvent.NEEDS);
        }
        var dispatcher:Dispatcher = new Dispatcher();
        dispatcher.dispatchEvent(event);
    }
}
}