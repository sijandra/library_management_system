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

import com.library.library_management_system.modules.book.domain.models.Book;
import com.library.library_management_system.modules.book.useCases.CreateBookUseCase;
import com.library.library_management_system.modules.book.useCases.GetAllBookUseCase;
import com.library.library_management_system.modules.book.useCases.GetBookByGenreIdUseCase;
import com.library.library_management_system.modules.book.useCases.GetBookByIdUseCase;
import com.library.library_management_system.modules.book.useCases.GetBookBySectionIdUseCase;
import com.library.library_management_system.modules.book.useCases.GetBookByTitleUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/book")
public class BookService {
    private final CreateBookUseCase createBookUseCase;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final GetBookByTitleUseCase getBookByTitleUseCase;
    private final GetBookByGenreIdUseCase getBookByGenreIdUseCase;
    private final GetBookBySectionIdUseCase getBookBySectionIdUseCase;
    private final GetAllBookUseCase getAllBookUseCase;
    private final JwtService jwtService;

    @Autowired
    public BookService(CreateBookUseCase createBookUseCase,
            GetBookByIdUseCase getBookByIdUseCase,
            GetBookByTitleUseCase getBookByTitleUseCase,
            GetBookByGenreIdUseCase getBookByGenreIdUseCase,
            GetBookBySectionIdUseCase getBookBySectionIdUseCase,
            GetAllBookUseCase getAllBookUseCase,
            JwtService jwtService) {
        this.createBookUseCase = createBookUseCase;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.getBookByTitleUseCase = getBookByTitleUseCase;
        this.getBookByGenreIdUseCase = getBookByGenreIdUseCase;
        this.getBookBySectionIdUseCase = getBookBySectionIdUseCase;
        this.getAllBookUseCase = getAllBookUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoBook() {
        return "Book service is working!";
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestHeader() String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);

        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Book> users = this.getAllBookUseCase.execute();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-book/{id}")
    public ResponseEntity<Book> getBookById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Book book = this.getBookByIdUseCase.execute(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestHeader() String token, @RequestBody Book book) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Book createdBook = this.createBookUseCase.execute(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@RequestHeader() String token,
            @PathVariable String title) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Book> book = this.getBookByTitleUseCase.execute(title);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @GetMapping("/get-by-genres")
    public ResponseEntity<List<Book>> getBooksByGenreId(@RequestHeader() String token,
            @RequestBody Long[] genreId) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Book> books = this.getBookByGenreIdUseCase.execute(genreId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get-by-section/{sectionId}")
    public ResponseEntity<List<Book>> getBooksBySectionId(@RequestHeader() String token,
            @PathVariable Long sectionId) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Book> books = this.getBookBySectionIdUseCase.execute(sectionId);
        return ResponseEntity.ok(books);
    }
}
