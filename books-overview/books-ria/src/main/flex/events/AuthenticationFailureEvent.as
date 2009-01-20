package events {
    import flash.events.Event;
    public class AuthenticationFailureEvent extends Event{
        public static var AUTHENTICATION_FAILURE:String = "authentication_failure";

        public var message:String;

        public function AuthenticationFailureEvent(eventType:String, message:String) {
            super(eventType, false, false);
            this.message = message;
        }
    }
}
