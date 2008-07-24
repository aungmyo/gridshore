package services {
    import mx.collections.ArrayCollection;
    import mx.rpc.events.ResultEvent;
    import model.BookSearchRequest;
    import services.RemoteService;

    /**
     * Remote object to the book manager for obtaining and storing books
     */
    public class BookService extends RemoteService {
        [Bindable]
        public var filteredBooks:ArrayCollection = new ArrayCollection();

        /**
         * Constructor
         */
        public function BookService() {
            super("bookService", "bookManager");
        }

        public function obtainFilteredBooks(bookSearchRequest:BookSearchRequest):void {
            remoteObject.obtainFilteredBooks.addEventListener(ResultEvent.RESULT, handleFilteredBooks);
            remoteObject.obtainFilteredBooks(bookSearchRequest);
        }

        private function handleFilteredBooks(event:ResultEvent):void {
            filteredBooks = event.result as ArrayCollection;
        }
    }
}
