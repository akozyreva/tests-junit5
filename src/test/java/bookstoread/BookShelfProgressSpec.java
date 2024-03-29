package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

// chapter 4. We use BooksParameterResolver.class to set the same set of test data
// for multiple test classes
@DisplayName("progress")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfProgressSpec {
    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void init(Map<String, Book> books) {
        shelf = new BookShelf();
        effectiveJava = books.get("Effective Java");
        codeComplete = books.get("Code Complete");
        mythicalManMonth = books.get("The Mythical Man-Month");
        cleanCode = books.get("Clean Code");
        refactoring = books.get("Refactoring: Improving the Design of Existing Code");
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode, refactoring);
    }

    @Test
    @DisplayName("is 0% completed and 100% to read when no book is read yet")
    void progress100PercentUnread(){
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(0);
        assertThat(progress.toRead()).isEqualTo(100);
    }

    @Test
    @DisplayName("is 40% completed and 60% to-read when 2 books are finished and 3 books not read yet")
    void progressWithCompletedAndToReadPercentages(){
        effectiveJava.startingReadingOn(LocalDate.of(2016, Month.JULY, 1));
        effectiveJava.finishedReadingOn(LocalDate.of(2016, Month.JULY, 31));
        cleanCode.startingReadingOn(LocalDate.of(2016, Month.AUGUST, 1));
        cleanCode.finishedReadingOn(LocalDate.of(2016, Month.AUGUST, 31));
        Progress progress = shelf.progress();
        assertThat(progress.completed()).isEqualTo(40);
        assertThat(progress.toRead()).isEqualTo(60);
    }
}