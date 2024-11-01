package com.library.library_management_system.modules.book.domain.services;

import java.util.List;

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

import com.library.library_management_system.modules.book.domain.models.Author;
import com.library.library_management_system.modules.book.useCases.CreateAuthorUseCase;
import com.library.library_management_system.modules.book.useCases.GetAllAuthorUseCase;
import com.library.library_management_system.modules.book.useCases.GetAuthorByIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/author")
public class AuthorService {
    private final CreateAuthorUseCase createAuthorUseCase;
    private final GetAuthorByIdUseCase getAuthorByIdUseCase;
    private final GetAllAuthorUseCase getAllAuthorUseCase;
    private final JwtService jwtService;

    @Autowired
    public AuthorService(CreateAuthorUseCase createAuthorUseCase, GetAuthorByIdUseCase getAuthorByIdUseCase,
            GetAllAuthorUseCase getAllAuthorUseCase,
            JwtService jwtService) {
        this.createAuthorUseCase = createAuthorUseCase;
        this.getAuthorByIdUseCase = getAuthorByIdUseCase;
        this.getAllAuthorUseCase = getAllAuthorUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoAuthor() {
        return "Author service is working!";
    }

    @GetMapping("/get-all-authors")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestHeader() String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Author> users = this.getAllAuthorUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-author/{id}")
    public ResponseEntity<Author> getAuthorById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Author author = this.getAuthorByIdUseCase.execute(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestHeader() String token, @RequestBody Author author) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Author createdAuthor = this.createAuthorUseCase.execute(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }
}
