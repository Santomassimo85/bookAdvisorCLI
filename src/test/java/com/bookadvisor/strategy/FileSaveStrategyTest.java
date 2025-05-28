package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the FileSaveStrategy class.
 * This test verifies that a BookDto object can be saved to a file using the FileSaveStrategy.
 */
public class FileSaveStrategyTest {

    /**
     * Tests the save method of FileSaveStrategy.
     * It creates a BookDto instance and attempts to save it to a file.
     */
    @Test
    public void testSaveToFile() {
        System.out.println("__________Testing FileSaveStrategy________________");
        FileSaveStrategy strategy = new FileSaveStrategy();
        BookDto book = new BookDto(
            "Test Title",
            "Test Author",
            "http://testcover.jpg",
            "2023",
            "falseKey",
            "This is a test description for the book."
        );
        strategy.save(book); // Saves the book to a file
    }
}
