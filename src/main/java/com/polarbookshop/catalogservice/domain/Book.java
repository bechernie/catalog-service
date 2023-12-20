package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "error.books.isbn.missing")
        @Pattern(message = "error.books.isbn.invalid", regexp = "^([0-9]{10}|[0-9]{13})$")
        String isbn,
        @NotBlank(message = "error.books.title.missing")
        String title,
        @NotBlank(message = "error.books.author.missing")
        String author,
        @NotNull(message = "error.books.price.missing")
        @Positive(message = "error.books.price.negative")
        Double price
) {

}
