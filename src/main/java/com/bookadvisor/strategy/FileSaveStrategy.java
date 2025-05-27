package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.service.BookLibraryService;

public class FileSaveStrategy implements SaveStrategy {

    private final BookLibraryService service = new BookLibraryService();

    @Override
    public void save(BookDto book) {
        service.saveBook(book);
    }
}
