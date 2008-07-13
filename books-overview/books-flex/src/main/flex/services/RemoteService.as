package services {
    import mx.rpc.remoting.RemoteObject;
    import events.RemoteExceptionEvent;
    import events.AuthenticationFailureEvent;
    import mx.core.Application;
    import mx.controls.Alert;
    import mx.rpc.events.FaultEvent;

    /**
     * Super class for all remote services that contains some generic methods.
     */
    public class RemoteService {
        private static var BAD_CREDENTIALS:String = "org.springframework.security.BadCredentialsException : Bad credentials";

        protected var remoteObject:RemoteObject;

        /**
         * Constructor accepting an id and destination for the actual RemoteObject to create. An event listener is added
         * for exceptions.
         * @param id String representing the id of the new RemoteObject to create
         * @param destination String representing the destination of the RemoteObject to create
         */
        public function RemoteService(id:String, destination:String) {
            remoteObject = new RemoteObject(id);
            remoteObject.destination = destination;
            remoteObject.addEventListener(FaultEvent.FAULT,onRemoteException);
        }

        /**
         * generic fault event handler for all remote object actions. based on the received message an action is taken,
         * mostly throwing a new event.
         * @param event FaultEvent received for handling
         */
        public function onRemoteException(event:FaultEvent):void {
            trace('code : ' + event.fault.faultCode + ', message : ' + event.fault.faultString + ',detail : ' + event.fault.faultDetail);
            if (event.fault.faultString == BAD_CREDENTIALS) {
                Application.application.dispatchEvent(
                        new AuthenticationFailureEvent(AuthenticationFailureEvent.AUTHENTICATION_FAILURE,"problem while authenticating"))
            } else {
                Application.application.dispatchEvent(
                        new RemoteExceptionEvent(RemoteExceptionEvent.REMOTE_EXCEPTION,
                                "unknown problem occurred during a remote call : " + event.fault.message));
            }
        }
    }
}