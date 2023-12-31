package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.IntegrationTest;
import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@IntegrationTest
class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void whenExistingBookFindBookByIsbnReturnsBook() {
        final var bookIsbn = "1234567890";
        final var book = Book.of(bookIsbn, "Title", "Author", 9.9, "Polarsophia");
        jdbcAggregateTemplate.insert(book);
        final var actualBook = bookRepository.findByIsbn(bookIsbn);
        assertThat(actualBook).isPresent().map(Book::isbn).contains(book.isbn());
    }
}
