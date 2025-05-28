package com.bookadvisor.annotations;

import java.lang.annotation.*;

/**
 * Indicates that the annotated method is considered important within the application.
 * <p>
 * This annotation can be used for documentation purposes or to trigger special processing
 * at runtime via reflection.
 * </p>
 *
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * {@code
 * @Important
 * public void criticalOperation() {
 *     // implementation
 * }
 * }
 * </pre>
 *
 * <p>
 * This annotation is retained at runtime and can only be applied to methods.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Important {}
