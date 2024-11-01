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

import com.library.library_management_system.modules.book.domain.models.Publisher;
import com.library.library_management_system.modules.book.useCases.CreatePublisherUseCase;
import com.library.library_management_system.modules.book.useCases.GetPublisherByIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/publisher")
public class PublisherService {
    private final CreatePublisherUseCase createPublisherUseCase;
    private final GetPublisherByIdUseCase getPublisherByIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public PublisherService(CreatePublisherUseCase createPublisherUseCase,
            GetPublisherByIdUseCase getPublisherByIdUseCase,
            JwtService jwtService) {
        this.createPublisherUseCase = createPublisherUseCase;
        this.getPublisherByIdUseCase = getPublisherByIdUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoPublisher() {
        return "Publisher service is working!";
    }

    @GetMapping("/get-publisher/{id}")
    public ResponseEntity<Publisher> getPublisherById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Publisher publisher = this.getPublisherByIdUseCase.execute(id);
        return publisher != null ? ResponseEntity.ok(publisher) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Publisher> createPublisher(@RequestHeader() String token, @RequestBody Publisher publisher) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Publisher createdPublisher = this.createPublisherUseCase.execute(publisher);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }
}
