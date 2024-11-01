package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library_management_system.modules.book.persistence.mappers.GenreSchema;

public interface IGenreRepository extends JpaRepository<GenreSchema, Long> {
    List<GenreSchema> findGenresByName(String name);
}
