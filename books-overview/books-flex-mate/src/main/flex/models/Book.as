package models {
import mx.collections.ArrayCollection;
[Bindable]
[RemoteClass(alias="nl.gridshore.samples.books.domain.Book")]
public class Book {
    public var id:Number;
    public var title:String;
    public var isbn:String;
    public var authors:ArrayCollection = new ArrayCollection();

    public function Book() {
    }
}
}