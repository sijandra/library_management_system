package com.library.library_management_system.modules.books.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.modules.books.domain.models.Books;
import com.library.library_management_system.modules.books.persistence.mappers.BooksMapper;
import com.library.library_management_system.modules.books.persistence.mappers.BooksSchema;

@Repository
public class BooksRepository {
    private final IBooksRepository booksRepository;

    @Autowired
    public BooksRepository(IBooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books getBooksById(Long id) {
        BooksSchema booksSchema = booksRepository.findById(id).orElse(null);
        return BooksMapper.toDomain(booksSchema);
    }

    public List<Books> getBooksByTitle(String title) {
        List<BooksSchema> booksSchemas = booksRepository.getBooksByTitleContaining(title);
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Books> getAvailableBooks() {
        List<BooksSchema> booksSchemas = booksRepository.getAvailableBooks();
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Books> getBooksBySectionId(String sectionId) {
        List<BooksSchema> booksSchemas = booksRepository.getBooksBySectionId(sectionId);
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<Books> getUnavailableBooks() {
        List<BooksSchema> booksSchemas = booksRepository.getUnavailableBooks();
        return booksSchemas.stream()
                .map(BooksMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Books createBooks(Books books) {
        BooksSchema booksSchema = BooksMapper.toPersistence(books);
        BooksSchema savedBooksSchema = booksRepository.save(booksSchema);
        return BooksMapper.toDomain(savedBooksSchema);
    }

    public Books updateBooksById(Long id, Books books) {
        books.setId(id);
        BooksSchema booksSchema = BooksMapper.toPersistence(books);
        BooksSchema updatedBooksSchema = booksRepository.save(booksSchema);
        return BooksMapper.toDomain(updatedBooksSchema);
    }
}
