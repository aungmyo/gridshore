package events {
import flash.events.Event;

public class MainNavigationEvent extends Event{
    public static const NAVIGATION:String = "mainnavigationevent";

    public static const AUTHENTICATION_FORM:String = "authenticationForm";
    public static const WELCOME_MESSAGE:String = "welcomeMessage";
    public static const ALL_BOOKS:String = "allBooks";
    public static const BOOK_DETAILS:String = "bookdetails";

    public var navigationId:String;

    public function MainNavigationEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
    }
}
}