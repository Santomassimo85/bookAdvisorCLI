package com.bookadvisor;

import org.junit.jupiter.api.Test;
import com.bookadvisor.service.BookService;
import com.bookadvisor.model.BookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BookService class.
 * <p>
 * These tests verify the behavior of the searchBooks method.
 */
public class BookServiceTest {

    /**
     * Tests that the searchBooks method returns a non-null and non-empty list
     * when searching for books with a sample query.
     */
    @Test
    public void testSearchReturnsResults() {
        System.out.println("__________Testing BookService searchBooks________________");
        BookService service = new BookService();

        // Execute the searchBooks method with a sample query
        List<BookDto> books = service.searchBooks("java");

        // Assert that the result is not null
        assertNotNull(books, "The returned list should not be null.");

        // Assert that the result contains at least one book
        assertFalse(books.isEmpty(), "The returned list should not be empty.");
    }
}
