package managers {

import com.asfusion.mate.events.Dispatcher;

import events.AuthenticationEvent;

import models.AuthorizationData;

import mx.collections.ArrayCollection;
import mx.rpc.Fault;

public class AuthenticationManager {
    [Bindable]
    public var faultMessage:String = "";

    private var dispatcher:Dispatcher = new Dispatcher();

    public function AuthenticationManager() {
    }

    public function storeAuthenticationProblem(obj:Object):void {
        var faultObj:Fault = Fault(obj);

        var BAD_CREDENTIALS:String = "Bad credentials";

        if (faultObj.faultString == BAD_CREDENTIALS) {
            faultMessage = "Something wrong with the credentials";
        } else {
            faultMessage = "Unknown problem occurred, please try again.";
        }
    }

    public function logout():void {
        faultMessage = "";
        AuthorizationData.getInstance().name = "";
        AuthorizationData.getInstance().roles = new ArrayCollection();

        dispatcher.dispatchEvent(new AuthenticationEvent(AuthenticationEvent.LOGGEDOUT));

    }

    public function logInSuccess(obj:Object):void {
        AuthorizationData.getInstance().roles = new ArrayCollection(obj.authorities);
        AuthorizationData.getInstance().name = obj.name;;
        
        var event:AuthenticationEvent = new AuthenticationEvent(AuthenticationEvent.AUTHENTICATED);
        var dispatcher:Dispatcher = new Dispatcher();
        dispatcher.dispatchEvent(event);
    }

    public function isAuthenticated(obj:Object):void {
        var event:AuthenticationEvent;
        if (obj) {
            event = new AuthenticationEvent(AuthenticationEvent.AUTHENTICATED);
        } else {
            event = new AuthenticationEvent(AuthenticationEvent.NEEDS);
        }
        var dispatcher:Dispatcher = new Dispatcher();
        dispatcher.dispatchEvent(event);
    }
}
}