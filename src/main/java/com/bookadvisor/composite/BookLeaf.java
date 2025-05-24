package com.bookadvisor.composite;

import com.bookadvisor.model.BookDto;
import java.util.List;
/**
 * Represents a leaf node in the composite pattern.
 * This class is used to represent a single book.
 */
public class BookLeaf extends BookComponent {
    private final BookDto book;
    /**
     * Constructor to create a BookLeaf instance.
     *
     * @param book The BookDto object representing the book.
     */

    public BookLeaf(BookDto book) {
        this.book = book;
    }
    /**
     * Returns a list containing the book represented by this BookLeaf.
     *
     * @return A list containing the book.
     */

    @Override
    public List<BookDto> toList() {
        return List.of(book);
    }
    /**
     * Displays the book information in a formatted manner.
     * The output includes the title and author of the book.
     */

    @Override
    public void display() {
        System.out.println("- " + book.getTitle() + " di " + book.getAuthor());
    }
}
