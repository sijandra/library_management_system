package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Genre;
import com.library.library_management_system.modules.book.persistence.repository.GenreRepository;

@Component
public class CreateGenreUseCase {
    private final GenreRepository genreRepository;

    @Autowired
    public CreateGenreUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre execute(Genre genre) {
        return this.genreRepository.createGenre(genre);
    }
}
