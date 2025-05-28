package com.bookadvisor.interfaces;

import com.bookadvisor.model.BookDto;
import java.util.List;

/**
 * Interface for saving book data.
 * Provides methods to save a single book or a list of books.
 */
public interface BookSaver {
    void save(BookDto book);
    void saveAll(List<BookDto> books);
}
