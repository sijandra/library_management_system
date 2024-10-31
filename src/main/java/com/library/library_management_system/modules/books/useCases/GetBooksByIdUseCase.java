package com.library.library_management_system.modules.books.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.repository.BooksRepository;

@Component
public class GetBooksByIdUseCase {
    private final BooksRepository booksRepository;

    @Autowired
    public GetBooksByIdUseCase(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books execute(Long id) {
        return this.booksRepository.getBooksById(id);
    }
}
