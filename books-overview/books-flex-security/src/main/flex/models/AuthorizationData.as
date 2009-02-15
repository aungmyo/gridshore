package models {

import mx.collections.ArrayCollection;
[Bindable]
[RemoteClass(alias="nl.gridshore.samples.books.web.security.vo.AuthorizationData")]
public class AuthorizationData {
    public var username:String;
    public var roles:ArrayCollection;
    
    public function AuthorizationData() {
    }


}
}