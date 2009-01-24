package managers {
import models.Book;
import models.BookFilter;
import mx.collections.ArrayCollection;
public class BooksManager {
    [Bindable]
    public var books:ArrayCollection = new ArrayCollection();

    [Bindable]
    public var currentBook:Book = new Book();

    private var booksFilter:BookFilter = new BookFilter();

    public function BooksManager() {
    }

    public function storeBooks(obj:Object):void {
        books = ArrayCollection(obj);
    }

    public function filterBooks(bookFilter:BookFilter):void {
        this.booksFilter = bookFilter;
        books.filterFunction = doFilterBooks;
        
        books.refresh();
    }

    public function clearFilterBooks():void {
        this.booksFilter = new BookFilter();
        books.refresh();
    }

    public function selectCurrentBook(book:Book):void {
        currentBook = book;
    }

    private function doFilterBooks(item:Object):Boolean {
        if (item.isbn != null && booksFilter.isbn != null && booksFilter.isbn != ""
                && item.isbn.toLowerCase().search(booksFilter.isbn.toLowerCase()) == -1) {
            return false;
        }
        if (item.title != null && booksFilter.title != null && booksFilter.title != ""
                && item.title.toLowerCase().search(booksFilter.title.toLowerCase()) == -1) {
            return false;
        }

        return true;
    }
}
}