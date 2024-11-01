package com.library.library_management_system.modules.book.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.domain.models.Author;
import com.library.library_management_system.modules.book.persistence.mappers.AuthorMapper;
import com.library.library_management_system.modules.book.persistence.mappers.AuthorSchema;

@Repository
public class AuthorRepository {
    private final IAuthorRepository authorRepository;

    @Autowired
    public AuthorRepository(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthor() {
        List<AuthorSchema> authorSchemas = authorRepository.findAll();
        List<Author> authorDomain = new ArrayList<>();

        for (AuthorSchema authorSchema : authorSchemas) {
            Author author = AuthorMapper.toDomain(authorSchema);
            authorDomain.add(author);
        }

        return authorDomain;
    }

    public Author getAuthorById(Long id) {
        AuthorSchema authorSchema = authorRepository.findById(id).orElse(null);
        return AuthorMapper.toDomain(authorSchema);
    }

    public List<Author> getAuthorByName(String name) {
        List<AuthorSchema> authorSchemas = authorRepository.findAuthorsByName(name);
        return authorSchemas.stream()
                .map(AuthorMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Author createAuthor(Author author) {
        AuthorSchema authorSchema = AuthorMapper.toPersistence(author);
        AuthorSchema savedAuthorSchema = authorRepository.save(authorSchema);
        return AuthorMapper.toDomain(savedAuthorSchema);
    }
}
