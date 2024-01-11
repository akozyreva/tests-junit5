package bookstoread;

import java.time.LocalDate;

public class Book implements Comparable<Book> {

    private final String title;
    private final String author;

    private final LocalDate publishedOn;

    public Book(String title, String author, LocalDate publishedOn) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ",publishedOn = " + publishedOn +
        '}';

    }


    // it must be implemented, because sorted uses it
    @Override
    public int compareTo(Book that) {
        // it shows, how exactly comparison should be done
        System.out.println(this.title.compareTo(that.title));
        return this.title.compareTo(that.title);
    }
}
