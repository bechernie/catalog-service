package com.polarbookshop.catalogservice.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        final var book = new Book("1234567890", "Title", "Author", 9.9);
        final var violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        final var book = new Book("ABCDEFGHIJ", "Title", "Author", 9.9);
        final var violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("error.books.isbn.invalid");
    }
}