package valueobjects {
    import mx.collections.ArrayCollection;
    [Bindable]
    [RemoteClass(alias="nl.gridshore.samples.books.domain.Author")]
    public class Author {
        public var id:Number;
        public var fullName:String;
        public var email:String;
        public var books:ArrayCollection;
    }
}
