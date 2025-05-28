package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.service.BookLibraryService;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*; 

/**
 * Unit test for the ConsoleNotifier class.
 * This test verifies that the update method prints a message to the console.
 */
public class ConsoleNotifierTest {

    /**
     * Tests that the update method of ConsoleNotifier prints a message to the console.
     */
    @Test
    public void testUpdatePrintsMessage() {
        System.out.println("__________Testing ConsoleNotifier________________");
        BookDto book = new BookDto("Test Title", "Author", "", "2023", "falseKey", "Test description...");
        ConsoleNotifier notifier = new ConsoleNotifier();
        notifier.update(book); // Should print something to the console
    }
}
