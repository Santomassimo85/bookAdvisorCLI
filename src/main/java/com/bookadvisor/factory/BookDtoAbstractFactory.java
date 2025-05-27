package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

public interface BookDtoAbstractFactory {
    BookDto create(String title, String author, String coverUrl, String publishDate, String description);
}
