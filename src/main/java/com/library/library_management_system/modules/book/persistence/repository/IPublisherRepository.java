package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library_management_system.modules.book.persistence.mappers.PublisherSchema;

public interface IPublisherRepository extends JpaRepository<PublisherSchema, Long> {
    List<PublisherSchema> findPublishersByName(String name);
}
