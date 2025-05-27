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
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Services
    private static final BookService bookService = new BookService();

    // Per tutto il resto
    private static final BookLibraryService libraryService = new BookLibraryService();


    // Per salvataggio con IoC
    private static final BookSaver saver = new BookLibraryService();

    // Strategia di salvataggio, può essere cambiata in ConsoleSaveStrategy per
    // testare
    private static SaveStrategy saveStrategy = new FileSaveStrategy();

    /**
     * Program start with interactive menu.
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
                case "0" -> System.out.println("👋 Uscita dal programma.");
                default -> System.out.println("❌ Opzione non valida.");
            }
        } while (!option.equals("0"));
    }

    private static void printMenu() {
        System.out.println("\n📚 Benvenuto in BookAdvisor CLI");
        System.out.println("1. Cerca e salva un libro");
        System.out.println("2. Visualizza libreria salvata");
        System.out.println("3. Svuota libreria");
        System.out.println("4. Rimuovi un libro dalla libreria");
        System.out.println("5. Mostra attributi BookDto (via Reflection)");
        System.out.println("6. Aggiungi manualmente un libro (Builder)");
        System.out.println("7. Cambia modalità di salvataggio (file/console)");
        System.out.println("0. Esci");
        System.out.print("Seleziona un'opzione: ");
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
     * Displays the books saved in the file.
     * If the library is empty, it informs the user.
     */
    private static void viewLibrary() {
        List<BookDto> books = libraryService.loadBooks();

        if (books.isEmpty()) {
            System.out.println("📭 La libreria è vuota.");
        } else {
            System.out.println("📚 Libri salvati:");
            for (int i = 0; i < books.size(); i++) {
                BookDto book = books.get(i);
                System.out.println((i + 1) + ". " + book.getTitle() + " - " + book.getAuthor() + " ("
                        + book.getPublishDate() + ")");
                String coverUrl = book.getCoverUrl();
                if (coverUrl == null || !coverUrl.startsWith("http")) {
                    System.out.println("   Copertina: Empty");
                } else if (!coverUrl.isEmpty()) {
                    System.out.println("   Copertina: " + coverUrl);
                } else {
                    System.out.println("   Copertina: N.D.");
                }
                String description = book.getDescription();
                if (description != null && !description.isEmpty()) {
                    System.out.println("   Descrizione: " + description);
                } else {
                    System.out.println("   Descrizione: N.D.");
                }
                System.out.println("   ");
            }
            System.out.println("Totale libri: " + books.size());
        }
    }

    /**
     * Removes a selected book from the library and updates the file.
     */
    private static void removeBook() {
        List<BookDto> books = libraryService.loadBooks();
        if (books.isEmpty()) {
            System.out.println("📭 La libreria è vuota.");
            return;
        }

        viewLibrary();
        System.out.print("🗑️ Inserisci il numero del libro da rimuovere: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < books.size()) {
                books.remove(index);
                libraryService.saveAllInternal(books);
                System.out.println("✅ Libro rimosso.");
            } else {
                System.out.println("❌ Scelta non valida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Input non valido.");
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
     * It uses reflection to print the fields of the BookDto class.
     * This is useful for understanding the structure of the DTO.
     */
    private static void printBookDtoFields() {
        try {
            Class<?> clazz = Class.forName("com.bookadvisor.model.BookDto");
            System.out.println("📘 Attributi BookDto:");
            Arrays.stream(clazz.getDeclaredFields()).forEach(
                    field -> System.out.println("- " + field.getName() + " : " + field.getType().getSimpleName()));
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Classe non trovata: " + e.getMessage());
        }
    }

    private static void addBookManually() {
        System.out.println("📝 Inserisci manualmente i dati del libro:");
        System.out.print("Titolo: ");
        String title = scanner.nextLine();
        System.out.print("Autore: ");
        String author = scanner.nextLine();
        System.out.print("Copertina (url): ");
        String coverUrl = scanner.nextLine();
        System.out.print("Anno di pubblicazione: ");
        String publishDate = scanner.nextLine();
       

        BookDto book = new BookDtoBuilder()
                .title(title)
                .author(author)
                .coverUrl(coverUrl)
                .publishDate(publishDate)
                .build();

        saveStrategy.save(book);
        System.out.println("✅ Libro aggiunto manualmente e salvato.");
    }

    /**
     * This allows switching between saving to a file and printing to the console.
     */
    private static void toggleSaveStrategy() {
        if (saveStrategy instanceof FileSaveStrategy) {
            saveStrategy = new ConsoleSaveStrategy();
            System.out.println("✅ Modalità salvataggio impostata su CONSOLE.");
        } else {
            saveStrategy = new FileSaveStrategy();
            System.out.println("✅ Modalità salvataggio impostata su FILE.");
        }
    }

}
