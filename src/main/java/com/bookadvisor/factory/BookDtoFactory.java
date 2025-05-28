package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

/**
 * Factory class for creating instances of BookDto.
 * This class provides a static method to create a BookDto object with the
 * specified parameters.
 * It encapsulates the construction logic and allows for easy creation of
 * BookDto objects.
 */
public class BookDtoFactory {

    /**
     * Creates a new instance of BookDto with the specified parameters.
     *
     * @param title       the title of the book
     * @param author      the author of the book
     * @param coverUrl    the URL of the book's cover image
     * @param publishDate the publication date of the book
     * @param key         the unique key or identifier for the book
     * @param description  the description of the book
     * @return a new instance of BookDto
     */
    public static BookDto create(String title, String author, String coverUrl, String publishDate, String key, String description) {
        // Constructs and returns a new BookDto object with the provided parameters
        return new BookDto(title, author, coverUrl, publishDate, key, description);
    }
}
