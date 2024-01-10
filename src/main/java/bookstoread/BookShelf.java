package bookstoread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class BookShelf {
    // final - final is only reference
    // private - it's not accessible as param
    private final List<String> books = new ArrayList<>();

    // it's getter of books
    public List<String> books() {
        return books;
    }

    public void add(String bookToAdd) {
        books.add(bookToAdd);
    }

    public void add(String... booksToAdd) {
        Arrays.stream(booksToAdd).forEach(book -> books.add(book));

    }
}