package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

/**
 * Builder class for creating instances of {@link BookDto}.
 * <p>
 * This builder allows you to set the properties of a BookDto object
 * using a fluent API.
 * </p>
 */
public class BookDtoBuilder {
    private String title;
    private String author;
    private String coverUrl;
    private String publishDate;
    private String key;
    private String description;

    /**
     * Sets the title of the book.
     *
     * @param title the book title
     * @return this builder instance
     */
    public BookDtoBuilder title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author name
     * @return this builder instance
     */
    public BookDtoBuilder author(String author) {
        this.author = author;
        return this;
    }

    /**
     * Sets the cover URL of the book.
     *
     * @param coverUrl the URL of the book cover image
     * @return this builder instance
     */
    public BookDtoBuilder coverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    /**
     * Sets the publish date of the book.
     *
     * @param publishDate the publish date in format YYYY-MM-DD
     * @return this builder instance
     */
    public BookDtoBuilder publishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    /**
     * Sets the unique key of the book.
     *
     * @param key the unique key
     * @return this builder instance
     */
    public BookDtoBuilder key(String key) {
        this.key = key;
        return this;
    }

    /**
     * Sets the description of the book.
     *
     * @param description the book description
     * @return this builder instance
     */
    public BookDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds and returns a new {@link BookDto} instance with the set properties.
     *
     * @return a new BookDto object
     */
    public BookDto build() {
        return new BookDto(title, author, coverUrl, publishDate, key, description);
    }
}
