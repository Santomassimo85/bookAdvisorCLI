package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;

/**
 * Strategy interface for saving a {@link BookDto} object.
 * <p>
 * Implementations of this interface define different ways to persist or store a book.
 */
public interface SaveStrategy {
    void save(BookDto book);
}
