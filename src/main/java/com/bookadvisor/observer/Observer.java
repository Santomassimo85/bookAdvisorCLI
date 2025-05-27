package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;

public interface Observer {
    void update(BookDto book);
}
