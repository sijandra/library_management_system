package com.library.library_management_system.modules.books.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.repository.BooksRepository;

@Component
public class UpdateBooksByIdUseCase {
    private final BooksRepository booksRepository;

    @Autowired
    public UpdateBooksByIdUseCase(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books execute(Long id, Books books) {
        return this.booksRepository.updateBooksById(id, books);
    }
}
