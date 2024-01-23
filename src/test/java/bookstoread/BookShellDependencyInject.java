package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// tests from chapter4
// in class BooksParameterResolver we assign default values
// and after that we get them from `init`
@ExtendWith(BooksParameterResolver.class)
public class BookShellDependencyInject {
    // here we're using class Book instead of String names
    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init(Map<String, Book> books) {
        shelf = new BookShelf();
        effectiveJava = books.get("Effective Java");
        codeComplete = books.get("Code Complete");
        mythicalManMonth = books.get("The Mythical Man-Month");
        cleanCode = books.get("Clean Code");
    }



    @Test
    @DisplayName("bookshelf is arranged lexicographically by book title")
    void bookShelfArrangedByBookTitle(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth) , books , () ->
                        "Books in a bookshelf should be arranged lexicographically by book title");
    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();

        List<Book> books = shelf.books();
        assertEquals(Arrays.asList(effectiveJava, codeComplete, mythicalManMonth), books,
                () -> "Books in bookshelf are in insertion order");
    }

    @Test
    void bookshelfArrangedByUserProvidedCriteria() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed(); //???
        assertThat(books).isSortedAccordingTo(reversed);
    }

    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map <Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();
        for (Map.Entry<Year, List<Book>> entry : booksByPublicationYear.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValues(Arrays.asList(effectiveJava, cleanCode));
        /* singleton - useful, when you want to return unchangeable list with one elem
        * */
        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(singletonList(codeComplete));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(1975))
                .containsValues(singletonList(mythicalManMonth));
    }

    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided criteria (group by author name)")
    void groupBooksByUserProvidedCriteria(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);

        assertThat(booksByAuthor).containsKey("Joshua Bloch").containsValues(singletonList(effectiveJava));

        assertThat(booksByAuthor).containsKey("Steve McConnel").containsValues(singletonList(codeComplete));
    }

    // example of nested.
    // it's not only group tests, but has it's own BeforeEach
    @Nested
    @DisplayName("search")
    public class BookShelfSearchSpec {

        @BeforeEach
        void setup(){
            shelf.add(codeComplete, effectiveJava, mythicalManMonth, cleanCode);
        }

        @Test
        @DisplayName("should find books with title containing text")
        void shouldFindBooksWithTitleContainingText(){
            List <Book> books = shelf.findBooksByTitle("code");
            assertThat(books.size()).isEqualTo(2);
        }
    }




}
