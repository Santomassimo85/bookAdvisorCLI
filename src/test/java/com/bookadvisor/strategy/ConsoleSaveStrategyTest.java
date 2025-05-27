package com.bookadvisor.strategy;

import com.bookadvisor.model.BookDto;
import org.junit.jupiter.api.Test;

public class ConsoleSaveStrategyTest {

    @Test
    public void testSavePrintsToConsole() {
        System.out.println("__________Testing ConsoleSaveStrategy________________");
        // Arrange
        ConsoleSaveStrategy strategy = new ConsoleSaveStrategy();
        BookDto book = new BookDto(
            "Console Strategy Book",
            "Console Author",
            "cover.jpg",
            "2024",
            "Test description via console");

        // Act
        strategy.save(book); // Deve stampare a console
    }
}
