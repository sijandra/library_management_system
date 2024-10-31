package com.library.library_management_system.modules.books.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.repository.BooksRepository;

@Component
public class GetAvailableBooksUseCase {
    private final BooksRepository booksRepository;

    @Autowired
    public GetAvailableBooksUseCase(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> execute() {
        return this.booksRepository.getAvailableBooks();
    }
}
