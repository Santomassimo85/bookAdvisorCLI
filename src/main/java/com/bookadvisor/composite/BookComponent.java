package com.bookadvisor.composite;

import com.bookadvisor.model.BookDto;
import java.util.List;

/**
 * The {@code BookComponent} class defines the base abstraction for the
 * Composite pattern
 * used to represent books (leaf) and groups of books in a hierarchical structure.
 * <p>
 * This abstract class provides default implementations for methods to manage
 * child components,
 * retrieve book data, and display information. Leaf components (individual
 * books) and composite
 * components (groups of books) should extend this class and override relevant
 * methods.
 * </p>
 *
 * <p>
 * Typical usage involves extending this class to create concrete leaf and
 * composite classes,
 * enabling uniform treatment of individual books and groups of books.
 * </p>
 *
 * <ul>
 * <li>{@link #add(BookComponent)}: Adds a child component (only for groups).</li>
 * <li>{@link #remove(BookComponent)}: Removes a child component (only for groups).</li>
 * <li>{@link #toList()}: Returns a list of {@code BookDto} objects contained in
 * this component.</li>
 * <li>{@link #display()}: Prints information about the book or group to the
 * console.</li>
 * </ul>
 *
 * @author Luca Santomassimo
 * @version 1.0
 */

public abstract class BookComponent {

    /**
     * Adds a child component to this component.
     * <p>
     * This method is intended to be overridden by composite classes. 
     * </p>
     * @throws UnsupportedOperationException if the operation is not supported
     * @param component
     */
    public void add(BookComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a child component from this component.
     * @throws UnsupportedOperationException if the operation is not supported
     * @param component
     */

    public void remove(BookComponent component) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of {@code BookDto} objects contained in this component.
     * @return a list of {@code BookDto} objects
     */

    public List<BookDto> toList() {
        throw new UnsupportedOperationException();
    }
/**
 * Displays information about the book or group to the console.
 * @return a representation of the book or group
 */
    public void display() {
        throw new UnsupportedOperationException();
    }
}
