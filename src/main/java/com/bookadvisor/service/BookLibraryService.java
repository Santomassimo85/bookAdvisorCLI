package com.bookadvisor.service;

import com.bookadvisor.model.BookDto;
import com.bookadvisor.util.AppLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manages the library saved in a text file.
 * Supports saving, loading, and persistent modification of books.
 */
public class BookLibraryService {
    /**
     * Logger for logging messages.
     * This can be used to log errors or important information.
     */
    private static final Logger logger = AppLogger.getLogger();

    /**
     * Returns the path to the file where the library is saved.
     * This can be overridden in subclasses to change the file location.
     * 
     * @return file path as a string
     */
    protected String getFilePath() {
        return "library.txt";
    }

    /**
     * Saves a single book by appending it to the end of the file.
     *
     * @param book book to save
     */
    public void saveBook(BookDto book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
            writer.write(serialize(book));
            writer.newLine();
            logger.info("✅ Libro salvato: " + book.getTitle());
        } catch (IOException e) {
            logger.severe("❌ Errore durante il salvataggio: " + e.getMessage());
        }
    }

    /**
     * Loads all books saved in the file.
     *
     * @return list of BookDto books
     */
    public List<BookDto> loadBooks() {
        List<BookDto> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(deserialize(line));
            }
            logger.info("📚 Libri caricati dal file: " + books.size());
        } catch (IOException e) {
            logger.warning("⚠️ Nessun file trovato o errore durante il caricamento: " + e.getMessage());
        }
        return books;
    }

    /**
     * Completely overwrites the file with a new list of books.
     *
     * @param books new list to save
     */
    public void saveAll(List<BookDto> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            for (BookDto book : books) {
                writer.write(serialize(book));
                writer.newLine();
            }
            logger.info("📄 Libreria sovrascritta con " + books.size() + " libri.");
        } catch (IOException e) {
            logger.severe("❌ Errore durante il salvataggio completo della libreria: " + e.getMessage());
        }
    }

    /**
     * Converts a BookDto into a string to save in the file.
     *
     * @param book book to convert
     * @return serialized string
     */
    private String serialize(BookDto book) {
        return book.getTitle() + "||" +
                book.getAuthor() + "||" +
                book.getCoverUrl() + "||" +
                book.getPublishDate() + "||" +
                book.getKey();
    }

    /**
     * Converts a line from the file into a BookDto object.
     *
     * @param line line read from the file
     * @return deserialized book
     */
    private BookDto deserialize(String line) {
        String[] parts = line.split("\\|\\|");
        return new BookDto(
                parts.length > 0 ? parts[0] : "",
                parts.length > 1 ? parts[1] : "",
                parts.length > 2 ? parts[2] : "",
                parts.length > 3 ? parts[3] : "",
                parts.length > 4 ? parts[4] : "");
    }

}
