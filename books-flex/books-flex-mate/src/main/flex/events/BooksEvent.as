package events {
import flash.events.Event;
import models.Book;
import models.BookFilter;
public class BooksEvent extends Event{
    public static const OBTAIN_ALL_BOOKS:String = "obtainallbooksevent";
    public static const FILTER_BOOKS:String = "filterbooksevent";
    public static const CLEAR_FILTER_BOOKS:String = "clearfilterbooksevent";
    public static const SELECT_CURRENT_BOOK:String = "selectecurrentbookevent";
    public static const CREATE_NEW_BOOK:String = "createnewbook";
    public static const STORE_BOOK:String = "storebook";

    public var bookFilter:BookFilter;

    public var currentBook:Book;

    public function BooksEvent(type:String, bubbles:Boolean = true, cancelable:Boolean = false) {
        super(type, bubbles, cancelable);
    }


}
}