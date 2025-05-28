package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

/**
 * Factory interface for creating instances of {@link BookDto}.
 * <p>
 * Implementations of this interface are responsible for instantiating
 * {@code BookDto} objects with the provided book details.
 * </p>
 */
public interface BookDtoAbstractFactory {
    BookDto create(String title, String author, String coverUrl, String publishDate, String key, String description);
}
