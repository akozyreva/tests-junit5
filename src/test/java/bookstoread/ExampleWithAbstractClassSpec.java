package bookstoread;

import org.junit.jupiter.api.*;

// in order not to duplicate @BeforeAll and @AfterAll
// every time you can create abstract class and make inheritance from it
abstract class DBConnectionPool{
    @BeforeAll
    static void connectDBConnectionPool() {
        System.out.println("before all hook");
    }
    @BeforeEach
    void initializeShelfWithDatabase() {
        System.out.println("before each hook");
    }
    @AfterAll
    static void closeDBConnectionPool() {
        System.out.println("after all hook");
    }
}

class ExampleWithAbstractClassSpec  extends DBConnectionPool{

    @Test
    void shouldGiveBackAllBooksInShelf() {
        // Check books in shelf
    }
    @Test
    void justRepeatTestToSeeHookWork() {
        // Check books in shelf
    }
    // to run hook once per class
    // annotate the class with @testListener(Lifecycle.per_CLass).
    @AfterEach
    void deleteCartFromDB() {
    }
}