package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;

public class ConsoleNotifier implements Observer {

    @Override
    public void update(BookDto book) {
        System.out.println("🔔__________________________ Libro notificato: " + book.getTitle());
    }
}
