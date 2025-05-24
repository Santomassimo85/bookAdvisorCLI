# BookAdvisor CLI

## a. Application Overview and Functionality

**BookAdvisor** is a command-line application (CLI) written in Java. It allows users to search for books online using the public OpenLibrary API, view book information, and manage a personal library saved to a file.

### Main Features:

- Enter a keyword to search for books.
- Display the top 10 results with title and author.
- Option to save a book to the personal library (saved in `library.txt`).
- View the saved library.
- Remove a book from the library.
- Completely clear the library.

---

## b. Technologies and Patterns Used (with Justification)

### Technologies:

- **Java 17**: main programming language.
- **Maven**: dependency management and build tool.
- **OpenLibrary API**: external data source.
- **JUnit 5**: unit testing.

### Design Patterns:

- **Factory Pattern** (`BookDtoFactory`): separates the creation of `BookDto` objects from application logic.
- **Composite Pattern** (`BookComponent`, `BookLeaf`, `BookGroup`): represents both single books and groups uniformly.
- **Iterator Pattern** (implicit): for iterating over lists and composite structures.
- **Exception Shielding** (`try/catch` in `BookService` and `BookLibraryService`): protects against errors during network or file system access.

---

## c. Setup and Execution Instructions

### Prerequisites:

- Java 17
- Maven

### Starting the application:

```bash
git clone <repo>
cd bookadvisor-cli
mvn clean compile
mvn exec:java -Dexec.mainClass="com.bookadvisor.Main"
```


## d. Diagram UML

```
@startuml
skinparam classAttributeIconSize 0
skinparam linetype ortho
skinparam packageStyle rectangle

package "service" {
  class BookService {
    + searchBooks(String): List<BookDto>
  }

  class BookLibraryService {
    + saveBook(BookDto)
    + loadBooks(): List<BookDto>
    + saveAll(List<BookDto>)
  }
}

package "model" {
  class BookDto{
  - title: String
    - author: String
    - coverUrl: String
    - publishDate: String
    - key: String
    + getTitle()
    + getAuthor()  
  }
  
}

package "factory" {
  class BookDtoFactory {
    + create(...): BookDto
  }
}

package "composite" {
  abstract class BookComponent {
    + add(BookComponent)
    + remove(BookComponent)
    + toList(): List<BookDto>
    + display()
  }

  class BookLeaf
  class BookGroup

  BookComponent <|-- BookLeaf
  BookComponent <|-- BookGroup
}


package "com.dookadvisor" {
  class Main
}

Main --> BookService
Main --> BookLibraryService
BookService --> BookDto
BookService --> BookDtoFactory
BookService --> BookGroup
BookGroup --> BookLeaf
BookLeaf --> BookDto
BookLibraryService --> BookDto
@enduml
```