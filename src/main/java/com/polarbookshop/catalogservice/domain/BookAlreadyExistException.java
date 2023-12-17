package com.polarbookshop.catalogservice.domain;

public class BookAlreadyExistException extends RuntimeException {
    private final String isbn;

    public BookAlreadyExistException(String isbn) {
        super("error.books.already.exists");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}
