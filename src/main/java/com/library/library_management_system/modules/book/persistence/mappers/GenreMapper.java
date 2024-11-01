package com.library.library_management_system.modules.book.persistence.mappers;

import com.library.library_management_system.modules.book.domain.models.Genre;

public class GenreMapper {
    private GenreMapper() {
    }

    public static Genre toDomain(GenreSchema genreSchema) {
        if (genreSchema == null) {
            return null;
        }

        return Genre.create(
                genreSchema.getId(),
                genreSchema.getName());
    }

    public static GenreSchema toPersistence(Genre genreDomain) {
        if (genreDomain == null) {
            return null;
        }

        GenreSchema genreSchema = new GenreSchema();
        genreSchema.setId(genreDomain.getId());
        genreSchema.setName(genreDomain.getName());

        return genreSchema;
    }
}
