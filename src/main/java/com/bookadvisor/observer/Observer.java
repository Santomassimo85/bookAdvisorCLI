package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;

/**
 * Observer interface for receiving updates about changes to a BookDto object.
 * Implementing classes should define the behavior to execute when a book is updated.
 */
public interface Observer {
    void update(BookDto book);
}
