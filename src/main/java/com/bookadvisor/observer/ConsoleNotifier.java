package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;

/**
 * ConsoleNotifier is an implementation of the Observer interface that receives notifications
 * about BookDto updates and prints a message to the console when a book is notified.
 */
public class ConsoleNotifier implements Observer {

    @Override
    public void update(BookDto book) {
        System.out.println("🔔__________________________ Libro notificato: " + book.getTitle());
    }
}
