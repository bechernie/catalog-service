package com.github.bechernie.catalogservice.demo;

import com.github.bechernie.catalogservice.domain.Book;
import com.github.bechernie.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        bookRepository.save(Book.of("1234567891", "Nothern Lights", "Lyra Silverstar", "Polarsophia", 9.90));
        bookRepository.save(Book.of("1234567892", "Polar Journey", "Iorek Polarson", "Polarsophia", 12.90));
    }
}
