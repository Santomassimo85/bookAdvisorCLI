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
 * Service class for managing a library of books stored in a text file.
 * Provides methods for saving, loading, and updating books persistently.
 * Supports observer notification for book save events.
 */
public class BookLibraryService implements BookSaver {

    /**
     * List of observers to be notified when a book is saved.
     * Observers can perform additional actions such as sending notifications or updating UI.
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Logger instance for logging informational and error messages.
     */
    private static final Logger logger = AppLogger.getInstance().getLogger();

    /**
     * Returns the path to the file where the library is stored.
     * Subclasses can override this method to change the file location.
     *
     * @return the file path as a string
     */
    protected String getFilePath() {
        return "library.txt";
    }

    /**
     * Saves a single book by appending it to the end of the file.
     * Notifies all registered observers after saving.
     *
     * @param book the book to save
     */
    @Important
    public void saveBook(BookDto book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
            writer.write(serialize(book));
            writer.newLine();
            notifyObservers(book);
            logger.info("✅ Book saved: " + book.getTitle());
        } catch (IOException e) {
            logger.severe("❌ Error while saving: " + e.getMessage());
        }
    }

    /**
     * Loads all books stored in the file.
     *
     * @return a list of BookDto objects loaded from the file
     */
    public List<BookDto> loadBooks() {
        List<BookDto> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(deserialize(line));
            }
            logger.info("📚 Books loaded from file: " + books.size());
        } catch (IOException e) {
            logger.warning("⚠️ No file found or error while loading: " + e.getMessage());
        }
        return books;
    }

    /**
     * Overwrites the file with a new list of books.
     *
     * @param books the new list of books to save
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
                            logger.warning("Error writing line: " + e.getMessage());
                        }
                    });
            logger.info("📄 Library overwritten with " + books.size() + " books.");
        } catch (IOException e) {
            logger.severe("❌ Error during full save: " + e.getMessage());
        }
    }

    /**
     * Saves a single book by delegating to saveBook.
     *
     * @param book the book to save
     */
    @Override
    public void save(BookDto book) {
        saveBook(book);
    }

    /**
     * Saves all books by delegating to saveAllInternal.
     *
     * @param books the list of books to save
     */
    @Override
    public void saveAll(List<BookDto> books) {
        saveAllInternal(books);
    }

    /**
     * Serializes a BookDto object into a string for file storage.
     *
     * @param book the book to serialize
     * @return the serialized string representation of the book
     */
    private String serialize(BookDto book) {
        return book.getTitle() + "||" +
                book.getAuthor() + "||" +
                book.getCoverUrl() + "||" +
                book.getPublishDate() + "||" +
                (book.getDescription() != null ? book.getDescription() : "");
    }

    /**
     * Deserializes a line from the file into a BookDto object.
     *
     * @param line the line read from the file
     * @return the deserialized BookDto object
     */
    private BookDto deserialize(String line) {
        String[] parts = line.split("\\|\\|");

        String title = parts.length > 0 ? parts[0] : "";
        String author = parts.length > 1 ? parts[1] : "";
        String coverUrl = parts.length > 2 ? parts[2] : "";
        String publishDate = parts.length > 3 ? parts[3] : "";
        String key = title + "||" + author + "||" + coverUrl + "||" + publishDate;
        String description = parts.length > 4 ? parts[4] : "";

        return new BookDto(title, author, coverUrl, publishDate, key, description);
    }

    /**
     * Registers an observer to be notified when a book is saved.
     *
     * @param o the observer to add
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Notifies all registered observers about a saved book.
     *
     * @param book the book that was saved
     */
    private void notifyObservers(BookDto book) {
        for (Observer o : observers) {
            o.update(book);
        }
    }

}
