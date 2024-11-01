package com.library.library_management_system.modules.books.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.books.persistence.mappers.BooksSchema;

@Repository
public interface IBooksRepository extends JpaRepository<BooksSchema, Long> {
    BooksSchema getBooksById(Long id);

    List<BooksSchema> getBooksByTitleContaining(String title);

    List<BooksSchema> getBooksBySectionId(String sectionId);
}
