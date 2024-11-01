package com.library.library_management_system.modules.books.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.repository.BooksRepository;

@Component
public class AddBookIdToBookIdsUseCase {
    private final BooksRepository booksRepository;

    @Autowired
    public AddBookIdToBookIdsUseCase(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books execute(Long booksId, Long newBookId) {
        return this.booksRepository.addBookIdToBookIds(booksId, newBookId);
    }
}
