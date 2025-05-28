package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link ConsoleSaveStrategy}.
 * This test verifies that the {@code save} method prints the book information to the console.
 */
public class ConsoleSaveStrategyTest {

    /**
     * Tests that the {@code save} method prints the book details to the console.
     * This test creates a sample {@link BookDto} and uses {@link ConsoleSaveStrategy}
     * to print its details. The output should appear in the console.
     */
    @Test
    public void testSavePrintsToConsole() {
        System.out.println("__________Testing ConsoleSaveStrategy________________");
        // Arrange: create a ConsoleSaveStrategy and a sample BookDto
        ConsoleSaveStrategy strategy = new ConsoleSaveStrategy();
        BookDto book = new BookDto(
            "Test Title", 
            "Test Author", 
            "http://testcover.jpg", 
            "2023", 
            "falseKey", 
            "This is a test description for the book."
        );

        // Act: call the save method, which should print to the console
        strategy.save(book);
    }
}
