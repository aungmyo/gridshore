package events {
    import flash.events.Event;
    public class RemoteExceptionEvent extends Event{
        public static var REMOTE_EXCEPTION:String = "remote_exception";

        public var message:String;

        public function RemoteExceptionEvent(eventType:String, message:String) {
            super(eventType, false, false);
            this.message = message;
        }
    }
}
