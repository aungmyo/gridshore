package services {
    import events.AuthenticationEvent;
    import mx.core.Application;
    import model.UserData;
    import mx.rpc.events.ResultEvent;

    /**
     * Class that extends the RemoteService class, therefore it makes use of the default error handling for
     * remote calls.
     */
    public class SecurityService extends RemoteService{

        /**
         * Default contructor for the SecurityService. This constructur creates the remote object used to connect
         * to the server for security related requests.
         */
        public function SecurityService() {
            super("securityService","authenticationHelper");
        }

        /**
         * Use the provided username and password to authenticate the principal. An exception is thrown if the
         * principal cannot be autenticated.
         * @param username String containing the username of the principal to authenticate
         * @param password Strin containing the password of the principal to authenticate
         */
        public function authenticatePrincipal(username:String,password:String):void {
            var userData:UserData = UserData.getInstance();
            userData.username = username;
            userData.password = password;
            remoteObject.authenticatePrincipal.addEventListener(
                    ResultEvent.RESULT,handleAuthenticatePrincipal);
            remoteObject.authenticatePrincipal(username,password);
        }

        /**
         * Handler method for the returned data of the remote service call authenticatePrincipal. A result will mean a
         * successfull authentication, therefore the UserData instance is set to initialized for a successfull authentication
         * with properties of the authenticated principal.
         * @param event
         */
        protected function handleAuthenticatePrincipal(event:ResultEvent):void {
            var userData:UserData = UserData.getInstance();
            userData.authenticated = true;

            var obj:Object = event.result;
            var obtainedRoles:Array = obj.roles as Array;
            for(var i:int=0; i < obtainedRoles.length; i++) {
                userData.addGrantedRole(obtainedRoles[i])
            }

            Application.application.dispatchEvent(
                    new AuthenticationEvent(AuthenticationEvent.AUTHENTICATION,"user is authenticated"));
        }
    }
}
