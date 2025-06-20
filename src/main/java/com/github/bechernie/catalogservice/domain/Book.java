package com.github.bechernie.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.*;

import java.time.Instant;

public record Book(
        @Id
        Long id,
        @NotBlank(message = "{isbn.defined}")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "{isbn.valid}")
        String isbn,
        @NotBlank(message = "{title.defined}")
        String title,
        @NotBlank(message = "{author.defined}")
        String author,
        @NotNull(message = "{price.defined}")
        @Positive(message = "{price.positive}")
        Double price,
        String publisher,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        @CreatedBy
        String createdBy,
        @LastModifiedBy
        String lastModifiedBy,
        @Version
        int version
) {
    public static Book of(String isbn, String title, String author, String publisher, Double price) {
        return new Book(null, isbn, title, author, price, publisher, null, null, null, null, 0);
    }
}
