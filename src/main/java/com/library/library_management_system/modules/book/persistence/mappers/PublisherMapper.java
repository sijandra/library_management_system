package com.library.library_management_system.modules.book.persistence.mappers;

import com.library.library_management_system.modules.book.domain.models.Publisher;

public class PublisherMapper {
    private PublisherMapper() {
    }

    public static Publisher toDomain(PublisherSchema publisherSchema) {
        if (publisherSchema == null) {
            return null;
        }

        return Publisher.create(
                publisherSchema.getId(),
                publisherSchema.getName());
    }

    public static PublisherSchema toPersistence(Publisher publisherDomain) {
        if (publisherDomain == null) {
            return null;
        }

        PublisherSchema publisherSchema = new PublisherSchema();
        publisherSchema.setId(publisherDomain.getId());
        publisherSchema.setName(publisherDomain.getName());

        return publisherSchema;
    }
}
