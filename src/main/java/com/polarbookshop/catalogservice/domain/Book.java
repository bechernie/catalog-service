package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book(
        @Id Long id,
        @NotBlank(message = "error.books.isbn.missing")
        @Pattern(message = "error.books.isbn.invalid", regexp = "^([0-9]{10}|[0-9]{13})$")
        String isbn,
        @NotBlank(message = "error.books.title.missing")
        String title,
        @NotBlank(message = "error.books.author.missing")
        String author,
        @NotNull(message = "error.books.price.missing")
        @Positive(message = "error.books.price.negative")
        Double price,
        @CreatedDate Instant createdDate,
        @LastModifiedDate Instant lastModifiedDate,
        @Version int version
) {

    public static Book of(String isbn, String title, String author, Double price) {
        return new Book(null, isbn, title, author, price, null, null, 0);
    }
}
