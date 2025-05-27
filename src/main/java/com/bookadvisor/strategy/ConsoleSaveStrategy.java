package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;

public class ConsoleSaveStrategy implements SaveStrategy {

    @Override
    public void save(BookDto book) {
        System.out.println("💾 Salvataggio simulato su console:");
        System.out.println("- " + book.getTitle() + " - " + book.getAuthor());
    }
}
