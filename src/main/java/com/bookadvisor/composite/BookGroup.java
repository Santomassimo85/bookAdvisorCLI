package com.bookadvisor.composite;

import com.bookadvisor.model.BookDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a composite node in the composite pattern.
 * This class represents a single book or a group of books.
 */
public class BookGroup extends BookComponent {
    private final List<BookComponent> children = new ArrayList<>();
    private final String name;

    /**
     * Constructor to create a BookGroup instance.
     *
     * @param name The name of the book group.
     */
    public BookGroup(String name) {
        this.name = name;
    }

    /**
     * Adds a child component to this book group.
     * <p>
     * This method is intended to be used for adding books or other book groups
     * to this group.
     * </p>
     *
     * @param component The child component to be added.
     */
    @Override
    public void add(BookComponent component) {
        children.add(component);
    }

    /**
     * Returns a list of BookDto objects contained in this book group.
     * 
     * @return A list of BookDto objects.
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
     * The output includes the name of the group and the details of each book
     * within it.
     */
    @Override
    public void display() {
        System.out.println("📁 " + name);
        for (BookComponent child : children) {
            child.display();
        }
    }
}
