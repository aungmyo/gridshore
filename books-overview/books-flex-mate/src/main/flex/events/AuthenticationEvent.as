package events {
import flash.events.Event;
public class AuthenticationEvent extends Event{
    public static const TRY:String = "tryauthenticationevent";

    public var username:String;
    public var password:String;

    public function AuthenticationEvent(type:String, bubbles:Boolean = true, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
    }
}
}