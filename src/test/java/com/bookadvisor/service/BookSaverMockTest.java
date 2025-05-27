package com.bookadvisor.service;

import com.bookadvisor.interfaces.BookSaver;
import com.bookadvisor.model.BookDto;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

/**
 * Test con Mockito che verifica l’interazione con un servizio di salvataggio
 * (BookSaver).
 */
public class BookSaverMockTest {
    
    @DisplayName("✅ Verifica chiamate ai metodi save() e saveAll() con Mockito")
    @Test
    public void testBookSaverCalled() {
        System.out.println("🩻____________MOCKITO TEST: Verifica chiamate ai metodi save() e saveAll()_____________🩻");

        // Step 1: Crea il mock
        BookSaver mockSaver = mock(BookSaver.class);
        System.out.println("✅ MOCK creato con successo");

        // Step 2: Crea un libro di test
        BookDto book = new BookDto("Test title", "Test author", "cover.jpg", "2024", "k123");
        System.out.println("📘 Libro di test creato: " + book.getTitle());

        // Step 3: Usa il mock
        mockSaver.save(book);
        mockSaver.saveAll(List.of(book));
        System.out.println("📤 Chiamate effettuate su save() e saveAll()");

        // Step 4: Verifica
        verify(mockSaver, times(1)).save(book);
        verify(mockSaver, times(1)).saveAll(List.of(book));
        System.out.println("✅ Verificato che i metodi sono stati chiamati una volta");
    }

}
