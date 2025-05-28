package com.bookadvisor.service;

import com.bookadvisor.interfaces.BookSaver;
import com.bookadvisor.model.BookDto;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

/**
 * Test class using Mockito to verify interactions with the BookSaver service.
 * This test ensures that the save() and saveAll() methods are called as expected.
 */
public class BookSaverMockTest {
    
    /**
     * ✅ Verifies calls to the save() and saveAll() methods using Mockito.
     */
    @DisplayName("✅ Verify calls to save() and saveAll() methods with Mockito")
    @Test
    public void testBookSaverCalled() {
        System.out.println("🩻____________MOCKITO TEST: Verifying calls to save() and saveAll() methods_____________🩻");

        // Step 1: Create the mock
        BookSaver mockSaver = mock(BookSaver.class);
        System.out.println("✅ MOCK created successfully");

        // Step 2: Create a test book
        BookDto book = new BookDto(
            "Test Book",
            "Test Author",
            "test-cover.jpg",
            "2023",
            "falseKey",
            "This is a test book description."
        );
        System.out.println("📘 Test book created: " + book.getTitle());

        // Step 3: Use the mock
        mockSaver.save(book);
        mockSaver.saveAll(List.of(book));
        System.out.println("📤 Calls made to save() and saveAll()");

        // Step 4: Verify method calls
        verify(mockSaver, times(1)).save(book);
        verify(mockSaver, times(1)).saveAll(List.of(book));
        System.out.println("✅ Verified that the methods were called once");
    }

}
