package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "polar.test-data.active", havingValue = "true")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    void loadTestData() {
        bookRepository.deleteAll();
        final var book1 = Book.of("1234567891", "Nothern Lights", "Lyra Silverstar", 9.9, "Polarsophia");
        final var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.9, "Polarsophia");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
