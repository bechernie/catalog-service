package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws IOException {
        final var book = new Book(1L, "1234567890", "Title", "Author", 9.9, "Polarsophia", Instant.parse("2007-12-03T10:15:30.00Z"), Instant.parse("2007-12-03T10:15:30.00Z"), 0);
        final var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo("1234567890");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo("Title");
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo("Author");
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo("Polarsophia");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(9.9);
        assertThat(jsonContent).extractingJsonPathStringValue("@.createdDate").isEqualTo("2007-12-03T10:15:30Z");
        assertThat(jsonContent).extractingJsonPathStringValue("@.lastModifiedDate").isEqualTo("2007-12-03T10:15:30Z");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.version").isEqualTo(0);
    }

    @Test
    void testDeserialize() throws IOException {
        final var content = """
                {
                    "id": 1,
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "publisher": "Polarsophia",
                    "price": 9.9,
                    "createdDate": "2007-12-03T10:15:30Z",
                    "lastModifiedDate": "2007-12-03T10:15:30Z",
                    "version": 0
                }
                """;
        assertThat(json.parse(content).getObject()).usingRecursiveComparison().isEqualTo(new Book(1L, "1234567890", "Title", "Author", 9.9, "Polarsophia", Instant.parse("2007-12-03T10:15:30.00Z"), Instant.parse("2007-12-03T10:15:30.00Z"), 0));
    }
}
