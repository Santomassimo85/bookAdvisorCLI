package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link BookDtoFactory} class.
 */
public class BookDtoFactoryTest {

    /**
     * Tests that the BookDtoFactory correctly creates a BookDto object
     * with the provided parameters.
     */
    @Test
    public void testFactoryCreatesBook() {
        System.out.println("__________Testing BookDtoFactory________________");
        BookDto book = BookDtoFactory.create(
            "Factory Book", "Author", "url", "2023", "falseKey", "Test description");

        // Verify that the title is set correctly
        assertEquals("Factory Book", book.getTitle());

        // Verify that the author is set correctly
        assertEquals("Author", book.getAuthor());

        // Verify that the publish date is set correctly
        assertEquals("2023", book.getPublishDate());
    }
}
