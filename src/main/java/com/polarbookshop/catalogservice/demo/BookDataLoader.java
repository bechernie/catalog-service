package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "polar.test-data.active", havingValue = "true")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    void loadTestData() {
        final var book1 = new Book("1234567891", "Nothern Lights", "Lyra Silverstar", 9.9);
        final var book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", 12.9);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
