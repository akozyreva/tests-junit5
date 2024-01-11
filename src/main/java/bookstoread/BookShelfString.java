package bookstoread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// left this class for leaving compatible old test example
public class BookShelfString {
    // final - final is only reference
    // private - it's not accessible as param
    private final List<String> books = new ArrayList<>();

    // it's getter of books
    public List<String> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(String... booksToAdd) {
        books.addAll(Arrays.asList(booksToAdd));

    }

    public List<String> arrange() {
        // return sorted new array List without changing existing one
        return books.stream().sorted().collect(Collectors.toList());
    }
}