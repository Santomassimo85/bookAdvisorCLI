package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;

public interface SaveStrategy {
    void save(BookDto book);
}
