package com.library.library_management_system.modules.book.persistence.mappers;

import com.library.library_management_system.modules.book.domain.models.Section;

public class SectionMapper {
    private SectionMapper() {
    }

    public static Section toDomain(SectionSchema sectionSchema) {
        if (sectionSchema == null) {
            return null;
        }

        return Section.create(
                sectionSchema.getId(),
                sectionSchema.getName());
    }

    public static SectionSchema toPersistence(Section sectionDomain) {
        if (sectionDomain == null) {
            return null;
        }

        SectionSchema sectionSchema = new SectionSchema();
        sectionSchema.setId(sectionDomain.getId());
        sectionSchema.setName(sectionDomain.getName());

        return sectionSchema;
    }
}
