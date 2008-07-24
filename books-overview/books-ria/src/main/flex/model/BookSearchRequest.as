package model {
[Bindable]
[RemoteClass(alias="nl.gridshore.samples.books.common.vo.BookSearchRequest")]
    public class BookSearchRequest {
        public var bookTitle:String;
        public var bookIsbn:String;
        public var authorFullName:String;
    }
}