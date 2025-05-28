package com.bookadvisor;

import com.bookadvisor.service.BookService;
import com.bookadvisor.service.BookLibraryService;
import com.bookadvisor.factory.BookDtoBuilder;
import com.bookadvisor.interfaces.BookSaver;
import com.bookadvisor.model.BookDto;
import com.bookadvisor.strategy.SaveStrategy;
import com.bookadvisor.strategy.FileSaveStrategy;
import com.bookadvisor.strategy.ConsoleSaveStrategy;

import java.util.*;

/**
 * BookAdvisor CLI - version with persistent library on file.
 * This is the main entry point for the BookAdvisor command-line application.
 * It allows users to search, add, remove, and view books in their library,
 * as well as switch between different save strategies (file or console).
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Service for searching books online
    private static final BookService bookService = new BookService();

    // Service for managing the local book library
    private static final BookLibraryService libraryService = new BookLibraryService();

    // BookSaver instance for saving books (using IoC)
    private static final BookSaver saver = new BookLibraryService();

    // Save strategy, can be switched between file and console
    private static SaveStrategy saveStrategy = new FileSaveStrategy();

    /**
     * Program entry point with interactive menu.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        String option;
        do {
            printMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1" -> searchAndSaveBook();
                case "2" -> viewLibrary();
                case "3" -> emptyLibrary();
                case "4" -> removeBook();
                case "5" -> printBookDtoFields();
                case "6" -> addBookManually();
                case "7" -> toggleSaveStrategy();
                case "0" -> System.out.println("👋 Exiting the program.");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (!option.equals("0"));
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println("\n📚 Welcome to BookAdvisor CLI");
        System.out.println("1. Search and save a book");
        System.out.println("2. View saved library");
        System.out.println("3. Empty library");
        System.out.println("4. Remove a book from the library");
        System.out.println("5. Show BookDto attributes (via Reflection)");
        System.out.println("6. Manually add a book (Builder)");
        System.out.println("7. Change save mode (file/console)");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    /**
     * Performs an online search and allows saving a selected book to the library.
     */
    private static void searchAndSaveBook() {
        System.out.print("\nEnter the title to search: ");
        String query = scanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("❌ Query cannot be empty.");
            return;
        }

        List<BookDto> books = bookService.searchBooks(query);
        if (books.isEmpty()) {
            System.out.println("🔍 No books found.");
        } else {
            System.out.println("\n👽 Results found:");
            for (int i = 0; i < books.size(); i++) {
                BookDto b = books.get(i);
                System.out.println((i + 1) + ". " + b.getTitle() + " by " + b.getAuthor());
            }

            System.out.print("\nSelect the number of the book to save (0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice > 0 && choice <= books.size()) {
                    saver.save(books.get(choice - 1));
                    System.out.println("✅ Book saved to your library.");
                } else if (choice != 0) {
                    System.out.println("❌ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    /**
     * Displays the books saved in the library.
     * If the library is empty, informs the user.
     */
    private static void viewLibrary() {
        List<BookDto> books = libraryService.loadBooks();

        if (books.isEmpty()) {
            System.out.println("📭 The library is empty.");
        } else {
            System.out.println("📚 Saved books:");
            for (int i = 0; i < books.size(); i++) {
                BookDto book = books.get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " - " + book.getAuthor() + " ("
                        + book.getPublishDate() + ")");
                String coverUrl = book.getCoverUrl();
                if (coverUrl == null || !coverUrl.startsWith("http")) {
                    System.out.println("   Cover: Empty");
                } else if (!coverUrl.isEmpty()) {
                    System.out.println("   Cover: " + coverUrl);
                } else {
                    System.out.println("   Cover: N/A");
                }
                String description = book.getDescription();
                if (description != null && !description.isEmpty()) {
                    System.out.println("   Description: " + description);
                } else {
                    System.out.println("   Description: N/A");
                }
                System.out.println("   ");
            }
            System.out.println("Total books: " + books.size());
        }
    }

    /**
     * Removes a selected book from the library and updates the file.
     */
    private static void removeBook() {
        List<BookDto> books = libraryService.loadBooks();
        if (books.isEmpty()) {
            System.out.println("📭 The library is empty.");
            return;
        }

        viewLibrary();
        System.out.print("🗑️ Enter the number of the book to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < books.size()) {
                books.remove(index);
                libraryService.saveAllInternal(books);
                System.out.println("✅ Book removed.");
            } else {
                System.out.println("❌ Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input.");
        }
    }

    /**
     * Completely empties the library saved in the file.
     */
    private static void emptyLibrary() {
        saver.saveAll(new ArrayList<>());
        System.out.println("🩻 Library emptied.");
    }

    /**
     * Uses reflection to print the fields of the BookDto class.
     * This is useful for understanding the structure of the DTO.
     */
    private static void printBookDtoFields() {
        try {
            Class<?> clazz = Class.forName("com.bookadvisor.model.BookDto");
            System.out.println("📘 BookDto attributes:");
            Arrays.stream(clazz.getDeclaredFields()).forEach(
                    field -> System.out.println("- " + field.getName() + " : " + field.getType().getSimpleName()));
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Class not found: " + e.getMessage());
        }
    }

    /**
     * Allows the user to manually enter book data and save it using the builder pattern.
     */
    private static void addBookManually() {
        System.out.println("📝 Enter the book data manually:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Cover (url): ");
        String coverUrl = scanner.nextLine();
        System.out.print("Publication year: ");
        String publishDate = scanner.nextLine();

        BookDto book = new BookDtoBuilder()
                .title(title)
                .author(author)
                .coverUrl(coverUrl)
                .publishDate(publishDate)
                .build();

        saveStrategy.save(book);
        System.out.println("✅ Book manually added and saved.");
    }

    /**
     * Allows switching between saving to a file and printing to the console.
     */
    private static void toggleSaveStrategy() {
        if (saveStrategy instanceof FileSaveStrategy) {
            saveStrategy = new ConsoleSaveStrategy();
            System.out.println("✅ Save mode set to CONSOLE.");
        } else {
            saveStrategy = new FileSaveStrategy();
            System.out.println("✅ Save mode set to FILE.");
        }
    }

}
