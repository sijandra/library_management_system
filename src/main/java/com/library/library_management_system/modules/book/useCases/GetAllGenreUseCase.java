package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Genre;
import com.library.library_management_system.modules.book.persistence.repository.GenreRepository;

@Component
public class GetAllGenreUseCase {
    private final GenreRepository genreRepository;

    @Autowired
    public GetAllGenreUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> execute() {
        return this.genreRepository.getAllGenre();
    }
}
