package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Author;
import com.library.library_management_system.modules.book.persistence.repository.AuthorRepository;

@Component
public class GetAuthorByIdUseCase {
    private final AuthorRepository authorRepository;

    @Autowired
    public GetAuthorByIdUseCase(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author execute(Long authorId) {
        return this.authorRepository.getAuthorById(authorId);
    }
}
