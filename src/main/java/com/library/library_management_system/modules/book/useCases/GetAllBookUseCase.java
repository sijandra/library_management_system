package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.persistence.repository.BookRepository;

@Component
public class GetAllBookUseCase {
    private final BookRepository bookRepository;

    @Autowired
    public GetAllBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> execute() {
        return this.bookRepository.getAllBooks();
    }
}
