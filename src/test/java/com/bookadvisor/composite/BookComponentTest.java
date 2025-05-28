package com.bookadvisor.composite;

import com.bookadvisor.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BookComponent composite structure.
 */
public class BookComponentTest {

    /**
     * Tests that the BookLeaf's toList method returns a list containing only itself.
     */
    @Test
    public void testBookLeafToList() {
        System.out.println("__________Testing BookLeaf toList________________");
        // Create a BookDto instance representing a single book
        BookDto book = new BookDto("Leaf Book", "Author", "", "2022", "falseKey", "Leaf description");
        // Wrap the BookDto in a BookLeaf
        BookLeaf leaf = new BookLeaf(book);
        // Convert the BookLeaf to a list
        List<BookDto> list = leaf.toList();

        // Assert that the list contains exactly one element
        assertEquals(1, list.size());
        // Assert that the title of the book in the list matches the expected value
        assertEquals("Leaf Book", list.get(0).getTitle());
    }
}
