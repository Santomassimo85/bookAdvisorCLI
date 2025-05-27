package com.bookadvisor.factory;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookDtoFactoryTest {

    @Test
    public void testFactoryCreatesBook() {
        System.out.println("__________Testing BookDtoFactory________________");
        BookDto book = BookDtoFactory.create(
            "Factory Book", "Author", "url", "2023", "Test description");

        assertEquals("Factory Book", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("2023", book.getPublishDate());
    }
}
