package com.library.library_management_system.modules.book.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Section;
import com.library.library_management_system.modules.book.persistence.repository.SectionRepository;

@Component
public class CreateSectionUseCase {
    private final SectionRepository sectionRepository;

    @Autowired
    public CreateSectionUseCase(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Section execute(Section section) {
        return this.sectionRepository.createSection(section);
    }
}
