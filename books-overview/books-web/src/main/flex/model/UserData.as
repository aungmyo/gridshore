package model {
    import mx.collections.ArrayCollection;

    [Bindable]
    public class UserData {

        private static var userData:UserData;

        public var username:String;
        public var password:String;
        public var grantedRoles:ArrayCollection;
        public var authenticated:Boolean;

        public static function getInstance():UserData {
            if (userData == null) {
                userData = new UserData();
                userData.grantedRoles = new ArrayCollection();
            }
            return userData;
        }

        public function addGrantedRole(role:String):void {
            grantedRoles.addItem(role);
        }

        public function isAdmin():Boolean {
            return hasRole("ROLE_ADMIN");
        }

        public function isUser():Boolean {
            return hasRole("ROLE_USER");
        }

        private function hasRole(role:String):Boolean {
            for (var i:String in grantedRoles) {
                if (grantedRoles[i] == role) {
                    return true;
                }
            }
            return false;
        }
    }
}
