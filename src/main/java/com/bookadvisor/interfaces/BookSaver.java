package com.bookadvisor.interfaces;

import com.bookadvisor.model.BookDto;
import java.util.List;

public interface BookSaver {
    void save(BookDto book);
    void saveAll(List<BookDto> books);
}
