package bookstoread;
import java.util.*;
import java.util.stream.Collectors;

public class BookShelf {
    // final - final is only reference
    // private - it's not accessible as param
    private final List<Book> books = new ArrayList<>();

    // it's getter of books
    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));

    }

    public List<Book> arrange() {
        // return sorted new array List without changing existing one
        return books.stream().sorted().collect(Collectors.toList());
    }
}