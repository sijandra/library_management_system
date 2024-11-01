package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Author;
import com.library.library_management_system.modules.book.persistence.repository.AuthorRepository;

@Component
public class GetAllAuthorUseCase {
    private final AuthorRepository authorRepository;

    @Autowired
    public GetAllAuthorUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> execute() {
        return this.authorRepository.getAllAuthor();
    }
}
