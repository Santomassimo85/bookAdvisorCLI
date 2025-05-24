package com.bookadvisor;

import org.junit.jupiter.api.Test;
import com.bookadvisor.service.BookService;
import com.bookadvisor.model.BookDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for BookService.
 * This test checks if the searchBooks method returns valid results.
 * 
 */
public class BookServiceTest {


    
    /**
     * Test to check if the searchBooks method returns a non-empty list of books.
     */
    @Test
    public void testSearchReturnsResults() {
        BookService service = new BookService();

        // Execute the searchBooks method with a sample query
        List<BookDto> books = service.searchBooks("java");

        // Check if the result is not null and not empty
        assertNotNull(books);

        // Check if the result contains at least one book
        assertFalse(books.isEmpty());
    }
}
