package com.bookadvisor;

import com.bookadvisor.factory.BookDtoBuilder;
import com.bookadvisor.model.BookDto;
import com.bookadvisor.service.BookLibraryService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Unit test for verifying that a book is correctly saved to a file.
 * This test creates a temporary file, saves a book to it using the BookLibraryService,
 * and checks that the file contains the expected content.
 */
public class BookFileWriteTest {

    /**
     * Tests that the book is correctly saved to a temporary file.
     * <p>
     * Steps:
     * <ul>
     *     <li>Create a temporary file</li>
     *     <li>Check that the file is initially empty</li>
     *     <li>Create a BookDto and save it using BookLibraryService</li>
     *     <li>Verify that the file contains the expected book data</li>
     *     <li>Clean up the temporary file</li>
     * </ul>
     *
     * @throws IOException If an I/O error occurs during file operations.
     */
    @Test
    public void testFileContainsSavedBook() throws IOException {
        System.out.println("___________Testing BookFileWrite________________");

        // Create a temporary file for testing
        Path tempFile = Files.createTempFile("test_book_", ".txt");
        System.out.println("📁 Temporary file created at: " + tempFile.toAbsolutePath());
        System.out.println("📄 File name: " + tempFile.getFileName());
        System.out.println("🗂️ Directory: " + tempFile.getParent());
        System.out.println("📊 File exists? " + Files.exists(tempFile));
        System.out.println("📏 Initial size: " + Files.size(tempFile) + " bytes");
        System.out.println();

        // Create a BookLibraryService instance that writes to the temporary file
        BookLibraryService tempService = new BookLibraryService() {
            @Override
            protected String getFilePath() {
                return tempFile.toString();
            }
        };

        try {
            // Check that the file is initially empty
            System.out.println("🔍 ========== CHECK INITIAL STATE ==========");
            List<String> initialLines = Files.readAllLines(tempFile);
            System.out.println("📖 Initial lines in file: " + initialLines.size());
            System.out.println("📝 Initial content: " + initialLines);
            assertEquals(0, initialLines.size(), "The file should be empty at the beginning");
            System.out.println("✅ Initial check completed");
            System.out.println();

            // Create and save a book
            System.out.println("💾 ========== SAVING BOOK ==========");

            // Build a BookDto instance
            BookDto book = new BookDtoBuilder()
                    .title("Java 101")
                    .author("Mario Rossi")
                    .coverUrl("cover.jpg")
                    .publishDate("2020")
                    .description("A beginner's guide to Java programming.")
                    .build();

            System.out.println("📚 Book created: " + book.getTitle() + " by " + book.getAuthor());
            System.out.println("🔄 Calling saveBook()...");

            tempService.saveBook(book);

            System.out.println("✅ saveBook() completed");
            System.out.println("📏 Size after saving: " + Files.size(tempFile) + " bytes");
            System.out.println();

            // Read and display the saved content
            System.out.println("🔍 ========== CHECK SAVED CONTENT ==========");
            List<String> lines = Files.readAllLines(tempFile);
            System.out.println("Total lines in file: " + lines.size());
            System.out.println("Full file content:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("   Line " + (i + 1) + ": " + lines.get(i));
            }
            System.out.println();

            // Assertions to verify file content
            System.out.println("========== PERFORMING ASSERTIONS ==========");
            System.out.println("🔎 Checking that there is exactly 1 line...");
            assertEquals(1, lines.size(), "The file should contain only one line.");
            System.out.println("✅ OK: File contains 1 line");

            System.out.println("🔎 Checking for 'Java 101'...");
            assertTrue(lines.get(0).contains("Java 101"), "The line should contain the title 'Java 101'");
            System.out.println("✅ OK: Title found");

            System.out.println("🔎 Checking for 'Mario Rossi'...");
            assertTrue(lines.get(0).contains("Mario Rossi"), "The line should contain the author 'Mario Rossi'");
            System.out.println("✅ OK: Author found");

            System.out.println("🔎 Checking for 'A beginner's guide to Java programming.'...");
            assertTrue(lines.get(0).contains("A beginner's guide to Java programming."), "The line should contain the description");
            System.out.println("✅ OK: Description found");

            System.out.println();
            System.out.println("========== ALL ASSERTIONS PASSED! ==========");

        } catch (Exception e) {
            System.out.println("========== ERROR DURING TEST ==========");
            System.out.println("Error type: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
            throw e;

        } finally {
            System.out.println();
            System.out.println("========== FINAL CLEANUP ==========");
            System.out.println("File exists before cleanup? " + Files.exists(tempFile));

            if (Files.exists(tempFile)) {
                System.out.println("Final file size: " + Files.size(tempFile) + " bytes");

                // Show final content before deleting (optional)
                try {
                    List<String> finalLines = Files.readAllLines(tempFile);
                    System.out.println("Final content before deletion:");
                    for (String line : finalLines) {
                        System.out.println("   " + line);
                    }
                } catch (IOException e) {
                    System.out.println("Unable to read final content: " + e.getMessage());
                }
            }

            System.out.println("Deleting the temporary file...");
            boolean deleted = Files.deleteIfExists(tempFile);
            System.out.println("✅ File deleted: " + deleted);
            System.out.println("File exists after deletion? " + Files.exists(tempFile));
            System.out.println("========== END OF TEST ==========");
        }
    }
}