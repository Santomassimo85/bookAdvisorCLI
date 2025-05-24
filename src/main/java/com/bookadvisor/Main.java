package com.bookadvisor;

import com.bookadvisor.service.BookService;
import com.bookadvisor.service.BookLibraryService;
import com.bookadvisor.model.BookDto;

import java.util.*;

/**
 * BookAdvisor CLI - version with persistent library on file.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Services
    private static final BookService bookService = new BookService();
    private static final BookLibraryService libraryService = new BookLibraryService();

    /**
     * Program start with interactive menu.
     */
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== BookAdvisor CLI ===");
            System.out.println("1 - Search books");
            System.out.println("2 - Show library");
            System.out.println("3 - Delete a book from the library");
            System.out.println("4 - Empty the entire library");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> searchAndSaveBook();
                case "2" -> showLibrary();
                case "3" -> deleteBook();
                case "4" -> emptyLibrary();
                case "0" -> exit = true;
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }

        System.out.println("😁 Exiting the program.");
    }

    /**
     * Performs online search and allows saving a book to file.
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
                    libraryService.saveBook(books.get(choice - 1));
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
     * Displays the books saved in the file.
     */
    private static void showLibrary() {
        List<BookDto> books = libraryService.loadBooks();

        System.out.println("\n🛅Your library contains:");
        if (books.isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (int i = 0; i < books.size(); i++) {
                BookDto b = books.get(i);
                System.out.println((i + 1) + ". " + b.getTitle() + " by " + b.getAuthor());
            }
        }
    }

    /**
     * Removes a selected book from the library and updates the file.
     */
    private static void deleteBook() {
        List<BookDto> books = libraryService.loadBooks();
        showLibrary();
        if (books.isEmpty()) return;

        System.out.print("\nEnter the number of the book to remove: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice > 0 && choice <= books.size()) {
                BookDto removed = books.remove(choice - 1);
                libraryService.saveAll(books);
                System.out.println("🗑️ Removed: " + removed.getTitle());
            } else {
                System.out.println("❌ Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number.");
        }
    }

    /**
     * Completely empties the library saved in the file.
     */
    private static void emptyLibrary() {
        libraryService.saveAll(new ArrayList<>());
        System.out.println("🩻 Library emptied.");
    }
}
