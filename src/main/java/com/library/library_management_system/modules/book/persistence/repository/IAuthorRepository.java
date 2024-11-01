package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library_management_system.modules.book.persistence.mappers.AuthorSchema;

public interface IAuthorRepository extends JpaRepository<AuthorSchema, Long> {
    List<AuthorSchema> findAuthorsByName(String name);
}
