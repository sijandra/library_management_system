package com.library.library_management_system.modules.books.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.repository.BooksRepository;

@Component
public class GetBooksByTitleUseCase {
    private final BooksRepository booksRepository;

    @Autowired
    public GetBooksByTitleUseCase(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> execute(String title) {
        return this.booksRepository.getBooksByTitle(title);
    }
}
