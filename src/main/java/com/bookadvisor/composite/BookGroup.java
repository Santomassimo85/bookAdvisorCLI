package com.bookadvisor.composite;

import com.bookadvisor.model.BookDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a composite node in the composite pattern.
 * This class can represent either a single book or a group of books.
 */
public class BookGroup extends BookComponent {
    // List of child components (books or groups of books)
    private final List<BookComponent> children = new ArrayList<>();
    // Name of the book group
    private final String name;

    /**
     * Constructs a BookGroup instance with the specified name.
     *
     * @param name The name of the book group.
     */
    public BookGroup(String name) {
        this.name = name;
    }

    /**
     * Adds a child component (either a book or another group) to this group.
     *
     * @param component The child component to add.
     */
    @Override
    public void add(BookComponent component) {
        children.add(component);
    }

    /**
     * Returns a list of BookDto objects contained in this book group.
     * This method recursively collects all books from the group and its children.
     *
     * @return A list of BookDto objects contained in this group.
     */
    @Override
    public List<BookDto> toList() {
        List<BookDto> result = new ArrayList<>();
        for (BookComponent child : children) {
            result.addAll(child.toList());
        }
        return result;
    }

    /**
     * Displays the book group information in a formatted manner.
     * The output includes the name of the group and the details of each book or subgroup within it.
     */
    @Override
    public void display() {
        System.out.println("📁 " + name);
        for (BookComponent child : children) {
            child.display();
        }
    }
}
