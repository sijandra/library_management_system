package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.persistence.mappers.BookSchema;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface IBookRepository extends JpaRepository<BookSchema, Long> {
    List<BookSchema> findByTitleContaining(String title);

    @Query("SELECT b FROM BookSchema b JOIN b.genreIds g WHERE g IN :genreIds")
    List<BookSchema> findByGenreIds(@Param("genreIds") Long[] genreIds);

    List<BookSchema> findBySectionId(Long sectionId);
}