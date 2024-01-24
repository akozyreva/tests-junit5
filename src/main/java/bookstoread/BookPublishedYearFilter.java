package bookstoread;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.function.Function;

// example of creating class by using interface
public class BookPublishedYearFilter implements BookFilter{

    private Function<LocalDate, Boolean> comparison;

    static BookPublishedYearFilter After(int year) {
        final LocalDate date = LocalDate.of(year, 12, 31);
        BookPublishedYearFilter filter = new BookPublishedYearFilter();
        filter.comparison = date::isBefore;
        return filter;
    }

    public static BookFilter Before(int year) {
        final LocalDate date = LocalDate.of(year, 1, 1);
        BookPublishedYearFilter filter = new BookPublishedYearFilter();
        filter.comparison = date::isAfter;
        return filter;
    }


    @Override
    public boolean apply(final Book b){
        return comparison.apply(b.getPublishedOn());
    }

}
