package bookstoread;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear(){
        return books.stream().collect(groupingBy(book -> Year.of(book.getPublishedOn().getYear())));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
        return books
                .stream()
                .collect(groupingBy(fx));
    }
}