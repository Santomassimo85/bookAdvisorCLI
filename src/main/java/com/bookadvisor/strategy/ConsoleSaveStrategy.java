package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;

/**
 * ConsoleSaveStrategy is an implementation of the SaveStrategy interface
 * that simulates saving a book by printing its details to the console.
 */
public class ConsoleSaveStrategy implements SaveStrategy {

    /**
     * Simulates saving a book by printing its details to the console.
     *
     * @param book the BookDto object containing book details to be "saved"
     */
    @Override
    public void save(BookDto book) {
        System.out.println("💾 Simulated save to console:");
        System.out.println("- " + book.getTitle() + " - " + book.getAuthor());
    }
}
