package security {
[Bindable]
[RemoteClass(alias="nl.gridshore.samples.books.web.security.vo.AuthorizationData")]
    public class AuthorizationControl {
        public var roles:Array;
        public var username:String;

        public function userIsAdmin():Boolean {
            for (var i:String in roles) {
                if (roles[i] == "ROLE_ADMIN") {
                    return true;
                }
            }
            return false;
        }
    }
}
