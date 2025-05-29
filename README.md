# BookAdvisor CLI

## a. Application Overview and Functionality

**BookAdvisor** is a command-line application (CLI) written in Java. It enables users to search for books online using the OpenLibrary API, view detailed book information, and manage a personal library stored locally.

### Main Features:

- Search for books by keyword.
- Display the top 10 results with title, author, and publication year.
- View detailed information for a selected book.
- Save books to a personal library (`library.txt`).
- List all books in the saved library.
- Remove individual books from the library.
- Clear the entire library.

---

## b. Technologies and Patterns Used (with Justification)

### Technologies:

- **Java 17**: core programming language.
- **Maven**: build automation and dependency management.
- **OpenLibrary API**: external book data provider.
- **JUnit 5**: unit and integration testing.

### Design Patterns:

- **Factory Pattern** (`BookDtoFactory`): encapsulates the creation of `BookDto` objects.
- **Composite Pattern** (`BookComponent`, `BookLeaf`, `BookGroup`): enables uniform treatment of single books and collections.
- **Iterator Pattern**: used for traversing book collections and composite structures.
- **Exception Handling**: robust error management in `BookService` and `BookLibraryService` for network and file operations.
- **Command Pattern**: encapsulates user actions as command objects for extensibility and testability.

---

## c. Setup and Execution Instructions

### Prerequisites:

- Java 17
- Maven

### Running the application:

```bash
git clone <repo>
cd bookadvisor-cli
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bookadvisor.Main"
```

---