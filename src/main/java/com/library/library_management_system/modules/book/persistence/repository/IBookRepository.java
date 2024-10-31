package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.persistence.mappers.BookSchema;

@Repository
public interface IBookRepository extends JpaRepository<BookSchema, Long> {
    List<BookSchema> findByTitleContaining(String title);

    List<BookSchema> findByGenreId(String genreId);

    List<BookSchema> findBySectionId(String sectionId);
}