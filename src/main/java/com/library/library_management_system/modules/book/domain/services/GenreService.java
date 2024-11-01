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

import com.library.library_management_system.modules.book.domain.models.Genre;
import com.library.library_management_system.modules.book.useCases.CreateGenreUseCase;
import com.library.library_management_system.modules.book.useCases.GetGenreByIdUseCase;
import com.library.library_management_system.modules.user.domain.services.JwtService;

@RestController
@RequestMapping(path = "api/lms/genre")
public class GenreService {
    private final CreateGenreUseCase createGenreUseCase;
    private final GetGenreByIdUseCase getGenreByIdUseCase;
    private final JwtService jwtService;

    @Autowired
    public GenreService(CreateGenreUseCase createGenreUseCase, GetGenreByIdUseCase getGenreByIdUseCase,
            JwtService jwtService) {
        this.createGenreUseCase = createGenreUseCase;
        this.getGenreByIdUseCase = getGenreByIdUseCase;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String demoGenre() {
        return "Genre service is working!";
    }

    @GetMapping("/get-genre/{id}")
    public ResponseEntity<Genre> getGenreById(@RequestHeader() String token, @PathVariable Long id) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Genre genre = this.getGenreByIdUseCase.execute(id);
        return genre != null ? ResponseEntity.ok(genre) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestHeader() String token, @RequestBody Genre genre) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String userId = jwtService.extractMetadata(token);
        if (!jwtService.validateToken(token, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Genre createdGenre = this.createGenreUseCase.execute(genre);
        return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
    }
}
