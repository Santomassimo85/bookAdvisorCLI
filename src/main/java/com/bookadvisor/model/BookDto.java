package com.bookadvisor.model;

/**
 * Data Transfer Object (DTO) for Book information.
 * <p>
 * This class is used to transfer book data between the client and server.
 * It encapsulates the main attributes of a book.
 */
public class BookDto {
    private String title;
    private String author;
    private String coverUrl;
    private String publishDate;
    private String key;
    private String description;

    /**
     * Constructs a new BookDto with the specified details.
     *
     * @param title       the title of the book
     * @param author      the author of the book
     * @param coverUrl    the URL of the book's cover image
     * @param publishDate the publication date of the book
     * @param key         a unique key or identifier for the book
     * @param description a brief description of the book
     */
    public BookDto(String title, String author, String coverUrl, String publishDate, String key, String description) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.publishDate = publishDate;
        this.key = key;
        this.description = description;
    }

    /**
     * Returns the title of the book.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the URL of the book's cover image.
     *
     * @return the cover image URL
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * Returns the publication date of the book.
     *
     * @return the publication date
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * Returns the unique key or identifier for the book.
     *
     * @return the book key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the description of the book.
     *
     * @return the book description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description the new description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
