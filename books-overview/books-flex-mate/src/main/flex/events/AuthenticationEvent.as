package events {
import flash.events.Event;
public class AuthenticationEvent extends Event{
    public static const TRY:String = "tryauthenticationevent";
    public static const CHECK_NEEDS:String = "checkneedsauthenticationevent";
    public static const NEEDS:String = "needsauthenticationevent";
    public static const FAILURE:String = "failureauthenticationevent";
    public static const AUTHENTICATED:String = "authenticatedevent";
    public static const LOGOUT:String = "logoutevent";

    public var username:String;
    public var password:String;
    public var failureMessage:String;

    public function AuthenticationEvent(type:String, bubbles:Boolean = true, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
    }
}
}