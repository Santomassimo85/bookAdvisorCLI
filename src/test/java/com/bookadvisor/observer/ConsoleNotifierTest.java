package com.bookadvisor.observer;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.service.BookLibraryService;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ConsoleNotifierTest {

   @Test
    public void testUpdatePrintsMessage() {
        System.out.println("__________Testing ConsoleNotifier________________");
        BookDto book = new BookDto("Test Title", "Author", "", "2023", "Test description...");
        ConsoleNotifier notifier = new ConsoleNotifier();
        notifier.update(book); // Dovrebbe stampare qualcosa in console
    }
}
