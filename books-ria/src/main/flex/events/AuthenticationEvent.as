package events {
    import flash.events.Event;
    public class AuthenticationEvent extends Event{
        public static const AUTHENTICATION:String = "authentication";
        public static const NEEDS_AUTHENTICATION:String = "needsauthentication";

        public var message:String;

        public function AuthenticationEvent(eventType:String, message:String) {
            super(eventType, false, false);
            this.message = message;
        }
    }
}
