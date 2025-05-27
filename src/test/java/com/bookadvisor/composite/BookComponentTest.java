package com.bookadvisor.composite;

import com.bookadvisor.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookComponentTest {

    @Test
    public void testBookLeafToList() {
        System.out.println("__________Testing BookLeaf toList________________");
        BookDto book = new BookDto("Leaf Book", "Author", "", "2022", "Leaf description");
        BookLeaf leaf = new BookLeaf(book);
        List<BookDto> list = leaf.toList();

        assertEquals(1, list.size());
        assertEquals("Leaf Book", list.get(0).getTitle());
    }
}
