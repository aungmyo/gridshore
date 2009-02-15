package managers{
import flexunit.framework.TestCase;
import models.Book;
import models.BookFilter;
import mx.collections.ArrayCollection;

public class BooksManagerTest extends TestCase {

    public function testNotContainsCaseInsensitive_existingstring():void {
        var booksManager:BooksManager = new BooksManager();
        var found:Boolean = booksManager.notContainsCaseInsensitive(
                "findme","Text that contains the key word findme somewhere in the middle");
        assertEquals(false,found);

    }

    public function testNotContainsCaseInsensitive_nonexistingstring():void {
        var booksManager:BooksManager = new BooksManager();
        var found:Boolean = booksManager.notContainsCaseInsensitive(
                "findmenot","Text that contains the key word findme somewhere in the middle");
        assertEquals(true,found);
    }

    public function testNotContainsCaseInsensitive_nullsearchfor():void {
        var booksManager:BooksManager = new BooksManager();
        var found:Boolean = booksManager.notContainsCaseInsensitive(
                null,"Text that contains the key word findme somewhere in the middle");
        assertEquals(false,found);
    }

    public function testNotContainsCaseInsensitive_nullsearchin():void {
        var booksManager:BooksManager = new BooksManager();
        var found:Boolean = booksManager.notContainsCaseInsensitive(
                "findmenot",null);
        assertEquals(false,found);
    }

    public function testNotContainsCaseInsensitive_emptystring():void {
        var booksManager:BooksManager = new BooksManager();
        var found:Boolean = booksManager.notContainsCaseInsensitive(
                "","Text that contains the key word findme somewhere in the middle");
        assertEquals(false,found);
    }

    public function testBooksFilter():void {
        var book1:Book = new Book();
        book1.id = 1;
        book1.isbn = "100100";
        book1.title = "Not filtered book";

        var book2:Book = new Book();
        book2.id = 2;
        book2.isbn = "200200";
        book2.title = "Filtered book";

        var books:ArrayCollection = new ArrayCollection();
        books.addItem(book1);
        books.addItem(book2);

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksFilter.isbn = "100";
        booksManager.filterBooks(booksFilter);

        assertEquals(1,booksManager.books.length);
    }
}
}