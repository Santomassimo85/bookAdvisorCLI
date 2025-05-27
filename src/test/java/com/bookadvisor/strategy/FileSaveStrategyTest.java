package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

public class FileSaveStrategyTest {

    @Test
    public void testSaveToFile() {
        System.out.println("__________Testing FileSaveStrategy________________");
        FileSaveStrategy strategy = new FileSaveStrategy();
        BookDto book = new BookDto("File Save Test", "Author", "", "2023", "Strategy test");
        strategy.save(book); // Salva il libro nel file
    }
}
