package com.library.library_management_system.modules.book.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.book.domain.models.Section;
import com.library.library_management_system.modules.book.persistence.mappers.SectionMapper;
import com.library.library_management_system.modules.book.persistence.mappers.SectionSchema;

@Repository
public class SectionRepository {
    private final ISectionRepository sectionRepository;

    @Autowired
    public SectionRepository(ISectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section getSectionById(Long id) {
        SectionSchema sectionSchema = sectionRepository.findById(id).orElse(null);
        return SectionMapper.toDomain(sectionSchema);
    }

    public List<Section> getSectionByName(String name) {
        List<SectionSchema> sectionSchemas = sectionRepository.findSectionsByName(name);
        return sectionSchemas.stream()
                .map(SectionMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Section createSection(Section section) {
        SectionSchema sectionSchema = SectionMapper.toPersistence(section);
        SectionSchema savedSectionSchema = sectionRepository.save(sectionSchema);
        return SectionMapper.toDomain(savedSectionSchema);
    }
}
