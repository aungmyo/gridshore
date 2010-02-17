package models {

import mx.collections.ArrayCollection;

import org.as3commons.lang.StringUtils;

[Bindable]
public class AuthorizationData {
    private static var authorizationData:AuthorizationData;
    private var _roles:ArrayCollection;
    public var name:String;

    public var admin:Boolean = false;
    public var user:Boolean = false;
    
    public function AuthorizationData() {
    }

    public static function getInstance():AuthorizationData {
        if (authorizationData == null) {
            authorizationData = new AuthorizationData();
            authorizationData.roles = new ArrayCollection();
        }
        return authorizationData;
    }


    public function get roles():ArrayCollection {
        return _roles;
    }

    public function set roles(value:ArrayCollection):void {
        _roles = value;
        admin = isAdmin();
        user = isUser();
    }

    private function isAdmin():Boolean {
        return hasRole("ROLE_ADMIN");
    }

    private function isUser():Boolean {
        return hasRole("ROLE_USER");
    }

    private function hasRole(role:String):Boolean {
        for (var i:String in roles) {
            if (StringUtils.equals(roles[i],role)) {
                return true;
            }
        }
        return false;
    }

}
}