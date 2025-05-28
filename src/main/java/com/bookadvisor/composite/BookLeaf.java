package com.bookadvisor.composite;

import com.bookadvisor.model.BookDto;
import java.util.List;

/**
 * Represents a leaf node in the composite pattern.
 * This class encapsulates a single book.
 */
public class BookLeaf extends BookComponent {
    private final BookDto book;

    /**
     * Constructs a BookLeaf instance with the specified book.
     *
     * @param book The BookDto object representing the book.
     */
    public BookLeaf(BookDto book) {
        this.book = book;
    }

    /**
     * Returns a list containing the book represented by this BookLeaf.
     *
     * @return A list containing the single BookDto instance.
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
        System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
    }
}
