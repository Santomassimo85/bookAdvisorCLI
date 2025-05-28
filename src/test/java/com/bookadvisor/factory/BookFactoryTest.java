package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link StandardBookFactory} class.
 * This class verifies that the factory correctly creates {@link BookDto} objects.
 */
public class BookFactoryTest {

    /**
     * Tests that the StandardBookFactory creates a BookDto with the expected properties.
     */
    @Test
    public void testStandardFactoryCreatesBook() {
        System.out.println("__________Testing StandardBookFactory________________");
        BookDtoAbstractFactory factory = new StandardBookFactory();

        BookDto book = factory.create(
            "The Lord of the Rings",
            "J.R.R. Tolkien",
            "http://cover.jpg",
            "1954",
            "falseKey",
            "An epic tale of a fantastic journey...");

        assertEquals("The Lord of the Rings", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertEquals("1954", book.getPublishDate());
        assertTrue(book.getDescription().startsWith("An epic"));
    }
}
