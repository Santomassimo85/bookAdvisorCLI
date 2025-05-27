package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookFactoryTest {

    @Test
    public void testStandardFactoryCreatesBook() {
        System.out.println("__________Testing StandardBookFactory________________");
        BookDtoAbstractFactory factory = new StandardBookFactory();

        BookDto book = factory.create(
            "Il Signore degli Anelli",
            "J.R.R. Tolkien",
            "http://cover.jpg",
            "1954",
            "Un'epica storia di un viaggio fantastico...");

        assertEquals("Il Signore degli Anelli", book.getTitle());
        assertEquals("J.R.R. Tolkien", book.getAuthor());
        assertEquals("1954", book.getPublishDate());
        assertTrue(book.getDescription().startsWith("Un'epica"));
    }
}
