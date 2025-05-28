package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

/**
 * StandardBookFactory is a concrete implementation of the BookDtoAbstractFactory interface.
 * It provides a method to create instances of BookDto with the given parameters.
 */
public class StandardBookFactory implements BookDtoAbstractFactory {

    /**
     * Creates a new BookDto object with the specified parameters.
     *
     * @param title       the title of the book
     * @param author      the author of the book
     * @param coverUrl    the URL of the book's cover image
     * @param publishDate the publication date of the book
     * @param key         the unique key or identifier for the book
     * @param description a brief description of the book
     * @return a new BookDto instance containing the provided information
     */
    @Override
    public BookDto create(String title, String author, String coverUrl, String publishDate, String key, String description) {
        // Create and return a new BookDto object with the provided parameters
        return new BookDto(title, author, coverUrl, publishDate, key, description);
    }
}
