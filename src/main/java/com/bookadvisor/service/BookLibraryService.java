package com.bookadvisor.service;

import com.bookadvisor.annotations.Important;
import com.bookadvisor.model.BookDto;
import com.bookadvisor.util.AppLogger;
import com.bookadvisor.interfaces.BookSaver;
import com.bookadvisor.observer.Observer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manages the library saved in a text file.
 * Supports saving, loading, and persistent modification of books.
 */

// public class BookLibraryService {

public class BookLibraryService implements BookSaver {

    /**
     * List of observers that will be notified when a book is saved.
     * This allows for additional actions, such as notifications or updates.
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Logger for logging messages.
     * This can be used to log errors or important information.
     */
    private static final Logger logger = AppLogger.getInstance().getLogger();

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
     * @Important
     *            This method is marked as important because it is a core
     *            functionality
     *            of the library service. It allows users to save books
     *            persistently.
     */
    @Important
    public void saveBook(BookDto book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
            writer.write(serialize(book));
            writer.newLine();
            notifyObservers(book);
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
    public void saveAllInternal(List<BookDto> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            books.stream()
                    .map(this::serialize)
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            logger.warning("Errore scrittura riga: " + e.getMessage());
                        }
                    });
            logger.info("📄 Libreria sovrascritta con " + books.size() + " libri.");
        } catch (IOException e) {
            logger.severe("❌ Errore durante il salvataggio totale: " + e.getMessage());
        }
    }

    @Override
    public void save(BookDto book) {
        saveBook(book);
    }

    @Override
    public void saveAll(List<BookDto> books) {
        saveAllInternal(books);
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
                (book.getDescription() != null ? book.getDescription() : "");
    }

    /**
     * Converts a line from the file into a BookDto object.
     *
     * @param line line read from the file
     * @return deserialized book
     */
    private BookDto deserialize(String line) {
        String[] parts = line.split("\\|\\|");

        String title = parts.length > 0 ? parts[0] : "";
        String author = parts.length > 1 ? parts[1] : "";
        String coverUrl = parts.length > 2 ? parts[2] : "";
        String publishDate = parts.length > 3 ? parts[3] : "";
        String description = parts.length > 4 ? parts[4] : "";

        return new BookDto(title, author, coverUrl, publishDate, description);
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    private void notifyObservers(BookDto book) {
        for (Observer o : observers) {
            o.update(book);
        }
    }

}
