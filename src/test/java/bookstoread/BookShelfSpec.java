package bookstoread;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

// in Junit5 class and method can be private
@DisplayName("<= BookShelf Specification =>")
class BookShelfSpec {
    // we can use no-arg constructor in test (< Junit5)
    // in Junit5 even private
    //    private BookShelfSpec() {
    //        /*
    //        This constructor is private
    //        */
    //    }

    // in Junit5 constructor with params is possible,
    // but only 1 constructor is allowed
    private BookShelfSpec(TestInfo testInfo) {
        System.out.println("Working on test " + testInfo.getDisplayName());
    }

    @Test
    // this will be visible in Test Results
    @DisplayName("is empty when no book is added to it")
    void shelfEmptyWhenNoBookAdded() {
        BookShelf shelf = new BookShelf();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    // in Junit5 it's also possible to pass params in test methods
    // with use of ParameterResolver API
    // it's not required to start/end with 'test' word
    @Test
    void shelfEmptyWhenNoBookAddedWithConstructor(TestInfo testInfo) {
        System.out.println("Working on test case " + testInfo.getDisplayName());
    }

    // built-in assertions
    // every assertXXX has 3 overloaded verions
    @Test
    void nullAssertionTest() {
        String str = null;
        assertNull(str);
        assertNull(str, "str should be null");
        assertNull(str, () -> "str should be null");
    }

    @Test
    void shouldCheckForEvenNumbers() {
        int number = new Random(10).nextInt();
        assertTrue(() -> number%2 == 0, number+ " is not an even number.");
        BiFunction<Integer, Integer, Boolean> divisible = (x, y) -> x % y == 0;
        Function<Integer, Boolean> multipleOf2 = (x) -> divisible.apply(x, 2);
        assertTrue(() -> multipleOf2.apply(number), () -> " 2 is not factor of " + number);
    }


    // if you need to fail test
    @Test
    void thisTestShouldFail() {
        fail(() -> "This test should fail");
    }


}