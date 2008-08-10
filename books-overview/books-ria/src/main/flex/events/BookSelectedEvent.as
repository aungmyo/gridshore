package events {
    import model.Book;
    import flash.events.Event;

    public class BookSelectedEvent extends Event {
        public static var SELECT_BOOK:String = 'selectBook';

        public var selectedBook:Book;

        public function BookSelectedEvent(eventType:String, selectedBook:Book) {
            super(eventType, false, false);
            this.selectedBook = selectedBook;
        }
    }
}