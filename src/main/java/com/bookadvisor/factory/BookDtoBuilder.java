package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

public class BookDtoBuilder {
    private String title;
    private String author;
    private String coverUrl;
    private String publishDate;
    private String key;
    private String description;

    public BookDtoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookDtoBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookDtoBuilder coverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public BookDtoBuilder publishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public BookDtoBuilder key(String key) {
        this.key = key;
        return this;
    }

    public BookDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public BookDto build() {
        return new BookDto(title, author, coverUrl, publishDate, description);
    }
}
