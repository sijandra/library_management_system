package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library_management_system.modules.book.persistence.mappers.SectionSchema;

public interface ISectionRepository extends JpaRepository<SectionSchema, Long> {
    List<SectionSchema> findSectionsByName(String name);
}
