package com.library.library_management_system.modules.books.domain.services;

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

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.useCases.CreateBooksUseCase;
import com.library.library_management_system.modules.books.useCases.GetBooksByIdUseCase;
import com.library.library_management_system.modules.books.useCases.GetBooksBySectionIdUseCase;
import com.library.library_management_system.modules.books.useCases.GetBooksByTitleUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/books")
public class BooksService {
    private final CreateBooksUseCase createBooksUseCase;
    private final GetBooksByIdUseCase getBooksByIdUseCase;
    private final GetBooksByTitleUseCase getBooksByTitleUseCase;
    private final GetBooksBySectionIdUseCase getBooksBySectionIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public BooksService(CreateBooksUseCase createBooksUseCase,
            GetBooksByIdUseCase getBooksByIdUseCase,
            GetBooksByTitleUseCase getBooksByTitleUseCase,
            GetBooksBySectionIdUseCase getBooksBySectionIdUseCase,
            JwtService jwtService) {
        this.createBooksUseCase = createBooksUseCase;
        this.getBooksByIdUseCase = getBooksByIdUseCase;
        this.getBooksByTitleUseCase = getBooksByTitleUseCase;
        this.getBooksBySectionIdUseCase = getBooksBySectionIdUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoBooks() {
        return "Books service is working!";
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<Books> getBooksById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Books book = this.getBooksByIdUseCase.execute(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Books> createBooks(@RequestHeader() String token, @RequestBody Books books) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Books createdBook = this.createBooksUseCase.execute(books);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<List<Books>> getBooksByTitle(@RequestHeader() String token, @PathVariable String title) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Books> books = this.getBooksByTitleUseCase.execute(title);
        return books != null ? ResponseEntity.ok(books) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get-by-section/{sectionId}")
    public ResponseEntity<List<Books>> getBooksBySectionId(@RequestHeader() String token,
            @PathVariable String sectionId) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Books> books = this.getBooksBySectionIdUseCase.execute(sectionId);
        return ResponseEntity.ok(books);
    }
}
