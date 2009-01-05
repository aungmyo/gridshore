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
        public function SecurityService(id:String, destination:String, endpoint:String) {
            super(id,destination,endpoint);
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
         * Method used to check with the server if there is an open session with a user that is already logged in
         */
        public function isPrincipalAuthenticatedBefore():void {
            remoteObject.checkUserIsAllreadyAuthenticated.addEventListener(ResultEvent.RESULT,
                    handlePreviouslyAuthenticated);
            remoteObject.checkUserIsAllreadyAuthenticated();
        }

        /**
         * Handler method for the returned data of the remote service call authenticatePrincipal. A result will mean a
         * successfull authentication, therefore the UserData instance is set to initialized for a successfull authentication
         * with properties of the authenticated principal.
         * @param event
         */
        protected function handleAuthenticatePrincipal(event:ResultEvent):void {
            var obj:Object = event.result;
            addAuthorizedRoles(obj);
        }

        /**
         * Method called when a call to the server is returned requesting the server for an existing authentication.
         * The previous authentication is successfull if the current user is not roleAnonymous.
         * @param event ResultEvent containing a nl.gridshore.samples.books.web.security.vo.AuthorizationData
         */
        protected function handlePreviouslyAuthenticated(event:ResultEvent):void {
            var obj:Object = event.result;
           if (obj.username != 'roleAnonymous') {
               addAuthorizedRoles(obj);
           } else {
               Application.application.dispatchEvent(
                    new AuthenticationEvent(AuthenticationEvent.NEEDS_AUTHENTICATION,"user needs to be authenticated"));
           }
        }

        /**
         * Utility method to analyse the incoming roles and make them available to the application
         * @param obj Transformed object from nl.gridshore.samples.books.web.security.vo.AuthorizationData
         */
        private function addAuthorizedRoles(obj:Object):void {
            var userData:UserData = UserData.getInstance();
            userData.authenticated = true;
            var obtainedRoles:Array = obj.roles as Array;
            for(var i:int=0; i < obtainedRoles.length; i++) {
                userData.addGrantedRole(obtainedRoles[i]);
            }

            Application.application.dispatchEvent(
                    new AuthenticationEvent(AuthenticationEvent.AUTHENTICATION,"user is authenticated"));
        }
    }
}
