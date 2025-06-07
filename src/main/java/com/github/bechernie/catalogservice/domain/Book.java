package com.github.bechernie.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record Book(
        @NotBlank(message = "{isbn.defined}")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "{isbn.valid}")
        String isbn,
        @NotBlank(message = "{title.defined}")
        String title,
        @NotBlank(message = "{author.defined}")
        String author,
        @NotNull(message = "{price.defined}")
        @Positive(message = "{price.positive}")
        Double price
) {
}
