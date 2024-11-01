package com.library.library_management_system.modules.book.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.library_management_system.modules.book.domain.models.Section;
import com.library.library_management_system.modules.book.persistence.repository.SectionRepository;

@Component
public class GetAllSectionUseCase {
    private final SectionRepository sectionRepository;

    @Autowired
    public GetAllSectionUseCase(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> execute() {
        return this.sectionRepository.getAllSection();
    }
}
