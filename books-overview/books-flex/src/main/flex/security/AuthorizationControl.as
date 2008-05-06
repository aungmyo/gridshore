package security {
    import mx.collections.ArrayCollection;
[Bindable]
[RemoteClass(alias="nl.gridshore.samples.books.web.security.vo.AuthorizationData")]
    public class AuthorizationControl {
        public var roles:Array;
        public var username:String;
    }
}
