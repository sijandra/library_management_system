package com.library.library_management_system.modules.book.persistence.mappers;

import com.library.library_management_system.modules.book.domain.models.Author;

public class AuthorMapper {
    private AuthorMapper() {
    }

    public static Author toDomain(AuthorSchema authorSchema) {
        if (authorSchema == null) {
            return null;
        }

        return Author.create(
                authorSchema.getId(),
                authorSchema.getName());
    }

    public static AuthorSchema toPersistence(Author authorDomain) {
        if (authorDomain == null) {
            return null;
        }

        AuthorSchema authorSchema = new AuthorSchema();
        authorSchema.setId(authorDomain.getId());
        authorSchema.setName(authorDomain.getName());

        return authorSchema;
    }
}
