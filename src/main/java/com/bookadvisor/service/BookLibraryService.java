package com.bookadvisor.service;

import com.bookadvisor.model.BookDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the library saved in a text file.
 * Supports saving, loading, and persistent modification of books.
 */
public class BookLibraryService {

    // Name of the file where we save the library
    // This file is created in the working directory of the project
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
        } catch (IOException e) {
            System.out.println("❌ Error while saving the book: " + e.getMessage());
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
        } catch (IOException e) {
            // if the file does not exist or is empty, return an empty list
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
        } catch (IOException e) {
            System.out.println("❌ Error while saving the library: " + e.getMessage());
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

        // Simple handling even if data is missing
        String title = parts.length > 0 ? parts[0] : "";
        String author = parts.length > 1 ? parts[1] : "";
        String coverUrl = parts.length > 2 ? parts[2] : "";
        String publishDate = parts.length > 3 ? parts[3] : "";
        String key = parts.length > 4 ? parts[4] : "";

        return new BookDto(title, author, coverUrl, publishDate, key);
    }
}
