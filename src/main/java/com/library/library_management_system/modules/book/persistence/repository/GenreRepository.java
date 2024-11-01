package com.library.library_management_system.modules.book.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.domain.models.Genre;
import com.library.library_management_system.modules.book.persistence.mappers.GenreMapper;
import com.library.library_management_system.modules.book.persistence.mappers.GenreSchema;

@Repository
public class GenreRepository {
    private final IGenreRepository genreRepository;

    @Autowired
    public GenreRepository(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenre() {
        List<GenreSchema> genreSchemas = genreRepository.findAll();
        List<Genre> genreDomain = new ArrayList<>();

        for (GenreSchema genreSchema : genreSchemas) {
            Genre genre = GenreMapper.toDomain(genreSchema);
            genreDomain.add(genre);
        }

        return genreDomain;
    }

    public Genre getGenreById(Long id) {
        GenreSchema genreSchema = genreRepository.findById(id).orElse(null);
        return GenreMapper.toDomain(genreSchema);
    }

    public List<Genre> getGenreByName(String name) {
        List<GenreSchema> genreSchemas = genreRepository.findGenresByName(name);
        return genreSchemas.stream()
                .map(GenreMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Genre createGenre(Genre genre) {
        GenreSchema genreSchema = GenreMapper.toPersistence(genre);
        GenreSchema savedGenreSchema = genreRepository.save(genreSchema);
        return GenreMapper.toDomain(savedGenreSchema);
    }
}
