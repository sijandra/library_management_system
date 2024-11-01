package com.library.library_management_system.modules.book.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.domain.models.Publisher;
import com.library.library_management_system.modules.book.persistence.mappers.PublisherMapper;
import com.library.library_management_system.modules.book.persistence.mappers.PublisherSchema;

@Repository
public class PublisherRepository {
    private final IPublisherRepository publisherRepository;

    @Autowired
    public PublisherRepository(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublisher() {
        List<PublisherSchema> publisherSchemas = publisherRepository.findAll();
        List<Publisher> publisherDomain = new ArrayList<>();

        for (PublisherSchema publisherSchema : publisherSchemas) {
            Publisher publisher = PublisherMapper.toDomain(publisherSchema);
            publisherDomain.add(publisher);
        }

        return publisherDomain;
    }

    public Publisher getPublisherById(Long id) {
        PublisherSchema publisherSchema = publisherRepository.findById(id).orElse(null);
        return PublisherMapper.toDomain(publisherSchema);
    }

    public List<Publisher> getPublisherByName(String name) {
        List<PublisherSchema> publisherSchemas = publisherRepository.findPublishersByName(name);
        return publisherSchemas.stream()
                .map(PublisherMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Publisher createPublisher(Publisher publisher) {
        PublisherSchema publisherSchema = PublisherMapper.toPersistence(publisher);
        PublisherSchema savedPublisherSchema = publisherRepository.save(publisherSchema);
        return PublisherMapper.toDomain(savedPublisherSchema);
    }
}
