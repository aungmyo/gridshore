package events {
import flash.events.Event;

public class AuthorsEvent  extends Event {
    public static const OBTAIN_ALL:String = "obtainallauthors";
    
    public function AuthorsEvent(type:String, bubbles:Boolean, cancelable:Boolean) {
        super(type, bubbles, cancelable);
    }
}
}