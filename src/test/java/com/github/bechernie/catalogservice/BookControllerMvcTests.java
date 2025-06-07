package com.github.bechernie.catalogservice;

import com.github.bechernie.catalogservice.domain.BookController;
import com.github.bechernie.catalogservice.domain.BookNotFoundException;
import com.github.bechernie.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        final var isbn = "1234567890";
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}
