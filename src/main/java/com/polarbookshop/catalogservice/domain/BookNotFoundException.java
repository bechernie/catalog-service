package com.polarbookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {
    private final String isbn;

    public BookNotFoundException(String isbn) {
        super("error.books.not.found");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
