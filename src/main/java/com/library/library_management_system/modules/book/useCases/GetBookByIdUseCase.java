package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.persistence.repository.BookRepository;

@Component
public class GetBookByIdUseCase {
    private final BookRepository bookRepository;

    @Autowired
    public GetBookByIdUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book execute(Long id) {
        return this.bookRepository.getBookById(id);
    }
}
