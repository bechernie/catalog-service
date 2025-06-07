package com.github.bechernie.catalogservice;


import com.github.bechernie.catalogservice.domain.Book;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        final var validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        final var book = Book.of("1234567890", "Title", "Author", "Polarsophia", 9.90);
        final var violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        final var book = Book.of("a234567890", "Title", "Author", "Polarsophia", 9.90);
        final var violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("{isbn.valid}");
    }
}
