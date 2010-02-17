package events {

import flash.events.Event;

public class AuthenticationEvent extends Event{
    // possible communication with back-end, mostly internal events or events thrown by using code
    public static const TRY:String = "tryauthenticationevent";
    public static const CHECK_NEEDS:String = "checkneedsauthenticationevent";
    public static const FAILURE:String = "failureauthenticationevent";
    public static const LOGOUT:String = "logoutevent";

    // events thrown by this code, using code can and should respond to these
    public static const NEEDS:String = "needsauthenticationevent";
    public static const AUTHENTICATED:String = "authenticatedevent";
    public static const LOGGEDOUT:String = "loggedout";

    public var username:String;
    public var password:String;
    public var failureMessage:String;

    public function AuthenticationEvent(type:String, bubbles:Boolean = true, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
    }
}
}