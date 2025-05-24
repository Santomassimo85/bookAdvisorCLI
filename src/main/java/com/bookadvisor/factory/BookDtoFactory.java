package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

/**
 * Factory class for creating instances of BookDto.
 * This class provides a static method to create a BookDto object with the specified parameters.
 * It encapsulates the construction logic and allows for easy creation of BookDto objects.
 */
public class BookDtoFactory {

    /**
     * Creates a new instance of BookDto with the specified parameters. 
     * @param title 
     * @param author
     * @param coverUrl
     * @param publishDate
     * @param key
     * @return A new instance of BookDto
     */

    public static BookDto create(String title, String author, String coverUrl, String publishDate, String key) {
        return new BookDto(title, author, coverUrl, publishDate, key);
    }
}
