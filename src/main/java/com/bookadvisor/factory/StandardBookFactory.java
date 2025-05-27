package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

public class StandardBookFactory implements BookDtoAbstractFactory {
    @Override
    public BookDto create(String title, String author, String coverUrl, String publishDate, String description) {
        return new BookDto(title, author, coverUrl, publishDate, description);
    }
}
