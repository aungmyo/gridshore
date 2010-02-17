package managers{
import flexunit.framework.TestCase;
import models.Author;
import models.Book;
import models.BookFilter;
import mx.collections.ArrayCollection;

public class BooksManagerTest extends TestCase {

    public function testBooksFilter_isbn():void {
        var books:ArrayCollection = createBooksFixture();

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksFilter.isbn = "100";
        booksManager.filterBooks(booksFilter);

        assertEquals(1,booksManager.books.length);
    }

    public function testBooksFilter_author():void {
        var books:ArrayCollection = createBooksFixture();

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksFilter.author = "marie";
        booksManager.filterBooks(booksFilter);

        assertEquals(1,booksManager.books.length);
    }

    public function testBooksFilter_empty():void {
        var books:ArrayCollection = createBooksFixture();

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksManager.filterBooks(booksFilter);

        assertEquals(2,booksManager.books.length);
    }

    public function testBooksFilter_noresults():void {
        var books:ArrayCollection = createBooksFixture();

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksFilter.title = "nooneknowsthis";
        booksManager.filterBooks(booksFilter);

        assertEquals(0,booksManager.books.length);
    }

    public function testBooksFilter_title():void {
        var books:ArrayCollection = createBooksFixture();

        var booksManager:BooksManager = new BooksManager();
        booksManager.books = books;

        var booksFilter:BookFilter = new BookFilter();
        booksFilter.title = "book";
        booksManager.filterBooks(booksFilter);

        assertEquals(2,booksManager.books.length);
    }

    private function createBooksFixture():ArrayCollection {
        var book1:Book = new Book();
        book1.id = 1;
        book1.isbn = "100100";
        book1.title = "Not filtered book";
        var dan:Author = new Author();
        dan.id = 1;
        dan.fullName = "Dan the man";
        dan.email = "dan@theman.com";
        book1.authors.addItem(dan);

        var book2:Book = new Book();
        book2.id = 2;
        book2.isbn = "200200";
        book2.title = "Filtered book";
        var cees:Author = new Author();
        cees.id = 2;
        cees.fullName = "Cees a Wees";
        cees.email = "cees@awees.com";
        book2.authors.addItem(cees);
        var marie:Author = new Author();
        marie.id = 3;
        marie.fullName = "Marie van Nie";
        marie.email = "marie@vannie.com";
        book2.authors.addItem(marie);


        var books:ArrayCollection = new ArrayCollection();
        books.addItem(book1);
        books.addItem(book2);

        return books;
    }
}
}