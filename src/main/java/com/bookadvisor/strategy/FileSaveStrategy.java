package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.service.BookLibraryService;

/**
 * Implementation of the {@link SaveStrategy} interface that saves a book using the {@link BookLibraryService}.
 * <p>
 * This strategy delegates the saving operation to the BookLibraryService, which handles the persistence logic.
 * </p>
 */
public class FileSaveStrategy implements SaveStrategy {

    private final BookLibraryService service = new BookLibraryService();

    @Override
    public void save(BookDto book) {
        service.saveBook(book);
    }
}
