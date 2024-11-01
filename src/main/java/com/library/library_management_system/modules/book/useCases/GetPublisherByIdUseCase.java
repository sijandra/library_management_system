package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Publisher;
import com.library.library_management_system.modules.book.persistence.repository.PublisherRepository;

@Component
public class GetPublisherByIdUseCase {
    private final PublisherRepository publisherRepository;

    @Autowired
    public GetPublisherByIdUseCase(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher execute(Long publisherId) {
        return this.publisherRepository.getPublisherById(publisherId);
    }
}
