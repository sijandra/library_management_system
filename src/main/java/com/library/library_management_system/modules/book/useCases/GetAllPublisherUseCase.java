package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Publisher;
import com.library.library_management_system.modules.book.persistence.repository.PublisherRepository;

@Component
public class GetAllPublisherUseCase {
    private final PublisherRepository publisherRepository;

    @Autowired
    public GetAllPublisherUseCase(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> execute() {
        return this.publisherRepository.getAllPublisher();
    }
}
