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
        if (notContainsCaseInsensitive(booksFilter.isbn, item.isbn)) {
            return false;
        }
        if (notContainsCaseInsensitive(booksFilter.title, item.title)) {
            return false;
        }

        var authors:ArrayCollection = item.authors;
        var foundAuthor:Boolean = false;
        for each (var author:Object in authors) {
            if (containsCaseInsensitive(booksFilter.author,author.fullName)) {
                foundAuthor = true;
                break;
            }
        }
        if (!foundAuthor) {
            return false;
        }

        return true;
    }

    private function notContainsCaseInsensitive(searchFor:String, searchIn:String):Boolean {
        return (searchIn != null && searchFor != null && searchFor != ""
                && searchIn.toLowerCase().search(searchFor.toLowerCase()) == -1);
    }

    private function containsCaseInsensitive(searchFor:String, searchIn:String):Boolean {
        return !notContainsCaseInsensitive(searchFor, searchIn);
    }
}
}