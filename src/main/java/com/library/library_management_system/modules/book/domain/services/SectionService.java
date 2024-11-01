package com.library.library_management_system.modules.book.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.modules.book.domain.models.Section;
import com.library.library_management_system.modules.book.useCases.CreateSectionUseCase;
import com.library.library_management_system.modules.book.useCases.GetSectionByIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/section")
public class SectionService {
    private final CreateSectionUseCase createSectionUseCase;
    private final GetSectionByIdUseCase getSectionByIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public SectionService(CreateSectionUseCase createSectionUseCase, GetSectionByIdUseCase getSectionByIdUseCase,
            JwtService jwtService) {
        this.createSectionUseCase = createSectionUseCase;
        this.getSectionByIdUseCase = getSectionByIdUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoSection() {
        return "Section service is working!";
    }

    @GetMapping("/get-section/{id}")
    public ResponseEntity<Section> getSectionById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Section section = this.getSectionByIdUseCase.execute(id);
        return section != null ? ResponseEntity.ok(section) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Section> createSection(@RequestHeader() String token, @RequestBody Section section) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Section createdSection = this.createSectionUseCase.execute(section);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }
}
